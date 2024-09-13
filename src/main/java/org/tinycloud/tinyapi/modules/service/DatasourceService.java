package org.tinycloud.tinyapi.modules.service;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.tinycloud.tinyapi.common.enums.TenantErrorCode;
import org.tinycloud.tinyapi.common.exception.TenantException;
import org.tinycloud.tinyapi.common.utils.db.DatasourceUtils;
import org.tinycloud.tinyapi.modules.bean.dto.DatasourceAddDto;
import org.tinycloud.tinyapi.modules.bean.entity.TDatasource;
import org.tinycloud.tinyapi.modules.bean.enums.DatasourceDriverEnum;
import org.tinycloud.tinyapi.modules.mapper.DatasourceMapper;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

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
    private DatasourceMapper datasourceMapper;

    /**
     * 用于缓存新生成的数据源
     */
    public static final Map<Long, HikariDataSource> dataSourceMap = new ConcurrentHashMap<Long, HikariDataSource>();

    /**
     * 用于缓存新生成的JdbcTemplate
     */
    public static final Map<Long, JdbcTemplate> JdbcTemplateMap = new ConcurrentHashMap<Long, JdbcTemplate>();

    public Boolean add(DatasourceAddDto dto) {
        // 第一步、校验链接地址的可用性
        DatasourceDriverEnum driverEnum = DatasourceDriverEnum.get(dto.getDatasourceType());
        DatasourceUtils datasourceUtils = new DatasourceUtils(dto.getDatasourceUrl(), dto.getDatasourceUsername(), dto.getDatasourcePassword(), driverEnum);
        boolean result = datasourceUtils.testConnection(driverEnum);
        datasourceUtils.release();
        if (!result) {
            throw new TenantException(TenantErrorCode.CONNECTION_TEST_QUERY_FAILED);
        }

        // 第二步、创建数据源并且放在缓存中
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(driverEnum.getDriver());
        dataSource.setJdbcUrl(dto.getDatasourceUrl());
        dataSource.setUsername(dto.getDatasourceUsername());
        dataSource.setPassword(dto.getDatasourcePassword());
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

        // TODO: 需要继续补充，还得判断是否需要加密的逻辑


        this.datasourceMapper.insert(tDatasource);

        dataSourceMap.put(tDatasource.getId(), dataSource);
        JdbcTemplateMap.put(tDatasource.getId(), new JdbcTemplate(dataSource));


        return true;
    }
}
