package org.tinycloud.tinyapi.modules.helper;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-14 13:04
 */
public class DatasourceCacheHelper {

    /**
     * 用于缓存新生成的数据源
     */
    public static final Map<Long, HikariDataSource> dataSourceMap = new ConcurrentHashMap<Long, HikariDataSource>();

    /**
     * 用于缓存新生成的JdbcTemplate
     */
    public static final Map<Long, JdbcTemplate> jdbcTemplateMap = new ConcurrentHashMap<Long, JdbcTemplate>();
}
