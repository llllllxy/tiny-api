package org.tinycloud.tinyapi.modules.web;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.tinycloud.tinyapi.common.model.ApiResult;
import org.tinycloud.tinyapi.modules.bean.dto.AuthenticationDto;
import org.tinycloud.tinyapi.modules.bean.dto.SignatureDto;
import org.tinycloud.tinyapi.modules.service.ApiAuthService;

/**
 * <p>
 *     restful接口鉴权-控制器
 * </p>
 *
 * @author liuxingyu01
 * @since 2023-12-04 12:43
 */
@RestController
@RequestMapping("/api/auth/v1")
public class ApiAuthController {

    @Autowired
    private ApiAuthService apiAuthService;

    @GetMapping("/authCode")
    public ApiResult<String> authCode() {
        return ApiResult.success(apiAuthService.authCode());
    }

    @PostMapping("/signature")
    public ApiResult<String> signature(@Validated @RequestBody SignatureDto dto) {
        return ApiResult.success(apiAuthService.signature(dto));
    }

    @PostMapping("/authentication")
    public ApiResult<String> authentication(@Validated @RequestBody AuthenticationDto dto, HttpServletRequest request) {
        return ApiResult.success(apiAuthService.authentication(dto, request));
    }

    // （0--不需认证1--简单认证2--签名认证），分为三个接口
}
