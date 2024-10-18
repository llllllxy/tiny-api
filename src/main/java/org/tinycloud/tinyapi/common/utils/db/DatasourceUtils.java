package org.tinycloud.tinyapi.common.utils.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinycloud.tinyapi.modules.bean.enums.DatasourceDriverEnum;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * 数据连接适配器,驱动需要另外引入
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class DatasourceUtils {
    private final static Logger logger = LoggerFactory.getLogger(DatasourceUtils.class);

    private JdbcUtils jdbcUtils = null;

    public DatasourceUtils(String url, String username, String password, DatasourceDriverEnum driverEnum) {
        String driver = driverEnum.getDriver();
        this.jdbcUtils = new JdbcUtils(driver, url, username, password);
    }

    public DatasourceUtils(String dataSourceBeanId) {
        this.jdbcUtils = new JdbcUtils(dataSourceBeanId);
    }

    public boolean testConnection(DatasourceDriverEnum driverEnum) {
        String testSql = driverEnum.getConnectionTestQuery();
        try {
            List<Map<String, Object>> list = this.jdbcUtils.selectByParams(testSql, null);
            if (list != null && !list.isEmpty()) {
                return true;
            }
        } catch (Exception e) {
            logger.error("数据源连接测试失败：", e);
        }
        return false;
    }

    /**
     * 更新数据
     *
     * @param sql 待操作的sql
     * @param params 待操作的sql参数
     * @return 结果
     */
    public int updateByParams(String sql, List<Object> params) throws SQLException {
        return this.jdbcUtils.updateByParams(sql, params);
    }

    /**
     * 查询多条记录
     *
     * @param sql 待操作的sql
     * @param params 待操作的sql参数
     * @return 结果
     */
    public List<Map<String, Object>> selectByParams(String sql, List<Object> params) throws SQLException {
        return this.jdbcUtils.selectByParams(sql, params);
    }

    /**
     * 释放连接
     */
    public void release() {
        this.jdbcUtils.release();
    }
}
