package org.tinycloud.tinyapi.modules.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.tinycloud.tinyapi.common.model.ApiResult;
import org.tinycloud.tinyapi.modules.bean.dto.DatasourceAddDto;
import org.tinycloud.tinyapi.modules.service.DatasourceService;


/**
 * <p>
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-2024/9/13 21:30
 */
@Slf4j
@RestController
@RequestMapping("/datasource")
public class DatasourceController {

    @Autowired
    private DatasourceService datasourceService;

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ApiResult<Boolean> add(@RequestBody @Validated DatasourceAddDto dto) {
        return ApiResult.success(this.datasourceService.add(dto), "新增成功！");
    }
}
