package org.tinycloud.tinyapi.common.utils.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinycloud.tinyapi.common.utils.SpringContextUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * JDBC工具类
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 10:42
 */
public class JdbcUtil {
    private static final Logger logger = LoggerFactory.getLogger(JdbcUtil.class);

    /**
     * 定义数据库的链接
     */
    private Connection conn;

    /**
     * 定义sql语句的执行对象
     */
    private PreparedStatement pstmt;

    /**
     * 定义查询返回的结果集合
     */
    private ResultSet rs;


    /**
     * 初始化
     *
     * @param driver   驱动
     * @param url      地址
     * @param username 用户名
     * @param password 密码
     */
    public JdbcUtil(String driver, String url, String username, String password) {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
            logger.info("数据库连接成功，url=：" + url);
        } catch (Exception e) {
            logger.error("数据库连接失败", e);
        }
    }

    /**
     * 初始化
     *
     * @param dataSourceBeanId beanId
     */
    public JdbcUtil(String dataSourceBeanId) {
        try {
            DataSource dataSource = (DataSource) SpringContextUtils.getBean(dataSourceBeanId);
            conn = dataSource.getConnection();
            logger.info("数据库连接成功，beanId：" + dataSourceBeanId);
        } catch (Exception e) {
            logger.error("数据库连接失败", e);
        }
    }

    /**
     * 更新数据
     *
     * @param sql    sql语句
     * @param params 参数
     */
    public boolean updateByParams(String sql, List<Object> params) throws SQLException {
        int result = -1;
        pstmt = conn.prepareStatement(sql);
        int index = 1;
        // 填充sql语句中的占位符
        if (null != params && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        result = pstmt.executeUpdate();
        return result >= 0;
    }

    /**
     * 查询多条记录
     *
     * @param sql    sql语句
     * @param params 参数
     */
    public List<Map<String, Object>> selectByParams(String sql, List<Object> params) throws SQLException {
        List<Map<String, Object>> list = new ArrayList<>();
        int index = 1;
        pstmt = conn.prepareStatement(sql);
        if (null != params && !params.isEmpty()) {
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(index++, params.get(i));
            }
        }
        rs = pstmt.executeQuery();
        ResultSetMetaData metaData = rs.getMetaData();
        int colCount = metaData.getColumnCount();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 1; i <= colCount; i++) {
                String columnLabel = metaData.getColumnLabel(i);
                Object columnValue = rs.getObject(i);
                if (null == columnValue) {
                    columnValue = "";
                }
                map.put(columnLabel, columnValue);
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 释放连接
     */
    public void release() {
        try {
            if (null != rs) {
                rs.close();
            }
            if (null != pstmt) {
                pstmt.close();
            }
            if (null != conn) {
                conn.close();
            }
        } catch (SQLException e) {
            logger.error("释放连接失败: ", e);
        }
    }
}
