package org.tinycloud.tinyapi.modules.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.tinycloud.tinyapi.common.constant.BusinessConstant;
import org.tinycloud.tinyapi.common.enums.CoreErrorCode;
import org.tinycloud.tinyapi.common.exception.CoreException;
import org.tinycloud.tinyapi.common.utils.JacksonUtils;
import org.tinycloud.tinyapi.common.utils.StrUtils;
import org.tinycloud.tinyapi.common.utils.web.RequestUtils;
import org.tinycloud.tinyapi.modules.bean.entity.TApiInfo;
import org.tinycloud.tinyapi.modules.bean.enums.IfPagingEnum;
import org.tinycloud.tinyapi.modules.mapper.ApiInfoMapper;
import org.tinycloud.tinyapi.modules.mapper.AppMapper;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 核心实现类
 * </p>
 *
 * @author liuxingyu01
 * @since 2024-09-2024/9/11 22:36
 */
@Slf4j
@Service
public class ApiClientService {

    @Autowired
    private ApiInfoMapper apiInfoMapper;

    @Autowired
    private AppMapper appMapper;

    public Object queryByUrl(HttpServletRequest request) {
        String method = request.getMethod();
        TApiInfo tApiInfo = getApiInfoByUrl(request.getRequestURI());
        Map<String, String> paramMap = getParamsMap(tApiInfo, request);

        // 判断 请求类型是否相同，如果不同，则返回错误
        String mockMethod = tApiInfo.getMethod();
        if (!mockMethod.equals(method)) {
            throw new CoreException(CoreErrorCode.API_METHOD_IS_NOT_MATCHED);
        }



        return null;
    }


    /**
     * 根据url获取api信息
     *
     * @param url 请求路径
     * @return api信息
     */
    private TApiInfo getApiInfoByUrl(String url) {
        if (url == null) {
            throw new CoreException(CoreErrorCode.API_ADDRESS_IS_NULL_ERROR);
        }
        url = url.replaceAll(".*" + BusinessConstant.URL_PREFIX, "");
        // 这里还应该过滤app_id（后续需要在拦截器里鉴权时加上ThreadLocal传递过来）
        // 同应用下的地址路径不允许重复
        List<TApiInfo> apiInfoList = this.apiInfoMapper.selectList(Wrappers.<TApiInfo>lambdaQuery()
                .eq(TApiInfo::getUrl, url.trim()));
        if (CollectionUtils.isEmpty(apiInfoList)) {
            throw new CoreException(CoreErrorCode.API_URL_PATH_IS_NOT_EXIST);
        } else {
            if (apiInfoList.size() > 1) {
                throw new CoreException(CoreErrorCode.API_URL_PATH_ARE_DUPLICATES);
            }
            return apiInfoList.get(0);
        }
    }


    /**
     * 从request获取参数列表
     *
     * @param request
     * @param tApiInfo
     * @return 参数
     */
    private Map<String, String> getParamsMap(TApiInfo tApiInfo, HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        String method = request.getMethod();
        if (method.equals(RequestMethod.GET.name())) {
            paramMap = RequestUtils.getParameterMap(request);
        } else if (method.equals(RequestMethod.POST.name())) {
            String contentType = request.getContentType();
            if (contentType.startsWith(MediaType.APPLICATION_JSON_VALUE)) {
                String body = RequestUtils.getBodyString(request);
                paramMap = JacksonUtils.readMap(body, String.class);
            } else {
                paramMap = RequestUtils.getParameterMap(request);
            }
        } else {
            throw new CoreException(CoreErrorCode.API_METHOD_IS_NOT_SUPPORT);
        }
        if (tApiInfo.getPaging().equals(IfPagingEnum.OPEN.getCode())) {
            if (!StrUtils.isNumeric(paramMap.get(BusinessConstant.PAGENO_PARAM_NAME))) {
                throw new CoreException(CoreErrorCode.THE_PAGINATION_PARAMETER_PAGENO_IS_MISSING);
            }
            if (!StrUtils.isNumeric(paramMap.get(BusinessConstant.PAGESIZE_PARAM_NAME))) {
                throw new CoreException(CoreErrorCode.THE_PAGINATION_PARAMETER_PAGESIZE_IS_MISSING);
            }
            paramMap.put("offset", request.getParameter("offset"));
            paramMap.put("limit", request.getParameter("limit"));
        }
        return paramMap;
    }

}
