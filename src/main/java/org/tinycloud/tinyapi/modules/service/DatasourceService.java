package org.tinycloud.tinyapi.modules.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tinycloud.tinyapi.common.constant.GlobalConstant;
import org.tinycloud.tinyapi.common.enums.TenantErrorCode;
import org.tinycloud.tinyapi.common.exception.TenantException;
import org.tinycloud.tinyapi.common.utils.cipher.SM2Utils;
import org.tinycloud.tinyapi.common.utils.db.DatasourceUtils;
import org.tinycloud.tinyapi.modules.bean.dto.DatasourceAddDto;
import org.tinycloud.tinyapi.modules.bean.entity.TDatasource;
import org.tinycloud.tinyapi.modules.bean.enums.DatasourceDriverEnum;
import org.tinycloud.tinyapi.modules.helper.DatasourceCacheHelper;
import org.tinycloud.tinyapi.modules.dao.DatasourceDao;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-13 21:31
 */
@Service
public class DatasourceService {

    @Autowired
    private DatasourceDao datasourceDao;

    public Boolean add(DatasourceAddDto dto) {
        // 第一步、校验链接地址的可用性
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

        TDatasource tDatasource = new TDatasource();
        tDatasource.setDatasourceType(dto.getDatasourceType());
        tDatasource.setDatasourceUrl(dto.getDatasourceUrl());
        tDatasource.setDatasourceUsername(dto.getDatasourceUsername());
        tDatasource.setDatasourcePassword(dto.getDatasourcePassword());
        tDatasource.setDatasourceName(dto.getDatasourceName());
        tDatasource.setStatus(GlobalConstant.ENABLED);
        tDatasource.setDelFlag(GlobalConstant.NOT_DELETED);
        tDatasource.setSecretKey(dto.getSecretKey());
        this.datasourceDao.insert(tDatasource);

        DatasourceCacheHelper.dataSourceMap.put(tDatasource.getId(), dataSource);
        DatasourceCacheHelper.jdbcTemplateMap.put(tDatasource.getId(), new JdbcTemplate(dataSource));

        return true;
    }
}
