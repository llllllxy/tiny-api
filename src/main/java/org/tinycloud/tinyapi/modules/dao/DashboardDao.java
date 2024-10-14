package org.tinycloud.tinyapi.modules.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-03-2024/3/24 14:52
 */
@Repository
public class DashboardDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> topList(Long tenantId, String today) {
        return null;
    }


    public List<Map<String, Object>> countByDateList(Long tenantId, List<String> dayList) {
        return null;
    }
}
