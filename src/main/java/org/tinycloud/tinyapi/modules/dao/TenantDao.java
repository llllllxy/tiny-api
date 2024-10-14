package org.tinycloud.tinyapi.modules.dao;

import org.springframework.stereotype.Repository;
import org.tinycloud.jdbc.BaseDao;
import org.tinycloud.tinyapi.modules.bean.entity.TTenant;


/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-12-05 15:12
 */
@Repository
public class TenantDao extends BaseDao<TTenant, Long> {
}
