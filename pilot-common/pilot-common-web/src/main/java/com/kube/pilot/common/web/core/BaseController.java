package com.kube.pilot.common.web.core;

import com.kube.pilot.common.core.domain.R;
import com.kube.pilot.common.core.utils.StringUtils;

/**
 * web层通用数据处理
 *
 * @author tongysh
 */
public class BaseController {

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected R<Void> toAjax(int rows) {
        return rows > 0 ? R.ok() : R.fail();
    }

    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected R<Void> toAjax(boolean result) {
        return result ? R.ok() : R.fail();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

}
