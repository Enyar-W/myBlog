package org.tl.blog.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.tl.blog.common.Interceptor.DateConvertEditor;
import org.tl.blog.common.log.service.SysLogService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 公共mvc处理
 * 用于放置公共service和mvc处理
 */
public class BaseController {

    @Autowired
    protected SysLogService sysLogService;

    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     * @param binder
     */
    @InitBinder
    public void initBinder(ServletRequestDataBinder binder) {
        binder.registerCustomEditor(Date.class, new DateConvertEditor());
    }

    /**
     * 抽取由逗号分隔的主键列表
     *
     * @param ids 由逗号分隔的多个主键值
     * @return 主键类表
     */
    protected List<String> extractIdListByComma(String ids) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.hasText(ids)) {
            for (String id : ids.split(",")) {
                if (StringUtils.hasLength(id)) {
                    result.add(id.trim());
                }
            }
        }

        return result;
    }

}
