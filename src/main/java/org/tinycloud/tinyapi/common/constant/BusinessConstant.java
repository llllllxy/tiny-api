package org.tinycloud.tinyapi.common.constant;

/**
 * <p>
 *     业务常量类
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-08-30 14:20
 */
public class BusinessConstant {

    /**
     *  默认业务缓存时效600秒
     */
    public static final Long CACHE_SESSION_TIMEOUT = 600L;

    /**
     * 租户项目 redis key
     */
    public static final String TENANT_PROJECT_REDIS_KEY = "tinyapi:project:";

    /**
     * 租户接口 redis key
     */
    public static final String TENANT_MOCK_REDIS_KEY = "tinyapi:mock:";

    /**
     * 统一接口前缀
     */
    public static final String URL_PREFIX = "/rest";

    /**
     * 接口分页参数名称pageNo
     */
    public static final String PAGENO_PARAM_NAME = "pageNo";

    /**
     * 接口分页参数名称pageSize
     */
    public static final String PAGESIZE_PARAM_NAME  = "pageSize";
}
