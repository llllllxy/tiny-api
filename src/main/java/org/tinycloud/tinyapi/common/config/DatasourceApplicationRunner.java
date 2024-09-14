package org.tinycloud.tinyapi.common.config;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.tinycloud.tinyapi.common.constant.GlobalConstant;
import org.tinycloud.tinyapi.common.enums.TenantErrorCode;
import org.tinycloud.tinyapi.common.exception.TenantException;
import org.tinycloud.tinyapi.common.utils.cipher.SM2Utils;
import org.tinycloud.tinyapi.common.utils.db.DatasourceUtils;
import org.tinycloud.tinyapi.modules.bean.entity.TDatasource;
import org.tinycloud.tinyapi.modules.bean.enums.DatasourceDriverEnum;
import org.tinycloud.tinyapi.modules.helper.DatasourceCacheHelper;
import org.tinycloud.tinyapi.modules.mapper.DatasourceMapper;

import java.util.List;

/**
 * <p>
 * 启动时自动运行的类
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-04-27 10:17
 */
@Component
@Order(98)
public class DatasourceApplicationRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(DatasourceApplicationRunner.class);

    @Autowired
    private Environment environment;

    @Autowired
    private DatasourceMapper datasourceMapper;

    @Override
    public void run(ApplicationArguments args) {
        logger.info("Initialization run start!");
        List<TDatasource> datasourceList = this.datasourceMapper.selectList(Wrappers.<TDatasource>lambdaQuery()
                .eq(TDatasource::getDelFlag, GlobalConstant.NOT_DELETED)
                .eq(TDatasource::getStatus, GlobalConstant.ENABLED));
        for (TDatasource dto : datasourceList) {
            try {
                DatasourceDriverEnum driverEnum = DatasourceDriverEnum.get(dto.getDatasourceType());
                DatasourceUtils datasourceUtils = new DatasourceUtils(dto.getDatasourceUrl(), dto.getDatasourceUsername(), dto.getDatasourcePassword(), driverEnum);
                boolean result = datasourceUtils.testConnection(driverEnum);
                datasourceUtils.release();
                if (!result) {
                    throw new TenantException(TenantErrorCode.CONNECTION_TEST_QUERY_FAILED);
                }
                // 如果密钥加密了，则解密了才能连接
                String password = dto.getDatasourcePassword();
                if (StringUtils.hasText(dto.getSecretKey())) { // 加密密钥
                    password = SM2Utils.decrypt(dto.getSecretKey(), dto.getDatasourcePassword());
                }
                // 第二步、创建数据源并且放在缓存中
                HikariDataSource dataSource = new HikariDataSource();
                dataSource.setDriverClassName(driverEnum.getDriver());
                dataSource.setJdbcUrl(dto.getDatasourceUrl());
                dataSource.setUsername(dto.getDatasourceUsername());
                dataSource.setPassword(password);
                // 租户数据源的其他配置和主数据源保存一致
                dataSource.setIdleTimeout(30000);
                dataSource.setMaximumPoolSize(15);
                dataSource.setMinimumIdle(10);
                dataSource.setMaxLifetime(1800000);
                dataSource.setConnectionTimeout(30000);
                dataSource.setConnectionTestQuery(driverEnum.getConnectionTestQuery());

                DatasourceCacheHelper.dataSourceMap.put(dto.getId(), dataSource);
                DatasourceCacheHelper.jdbcTemplateMap.put(dto.getId(), new JdbcTemplate(dataSource));
            } catch (Exception ex) {
                dto.setDatasourcePassword(null);
                logger.error("init tenant datasource failed dto: {}", dto);
                logger.error("init tenant datasource failed ex: ", ex);
            }
        }
        logger.info("Initialization run end!");
    }

}
