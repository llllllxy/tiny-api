package org.tinycloud.tinyapi.modules.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tinycloud.tinyapi.common.constant.BusinessConstant;
import org.tinycloud.tinyapi.common.model.ApiResult;
import org.tinycloud.tinyapi.modules.service.ApiClientService;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-11 22:35
 */

@Slf4j
@RestController
@RequestMapping("/api/client/v1")
public class ApiClientController {



    @Autowired
    private ApiClientService apiClientService;

    /**
     * 通用数据服务接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = BusinessConstant.URL_PREFIX + "/**", method = {RequestMethod.GET, RequestMethod.POST})
    public ApiResult<Object> queryByUrl(HttpServletRequest request) {
        return ApiResult.success(this.apiClientService.queryByUrl(request), "获取成功！");
    }

}
