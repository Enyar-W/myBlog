package org.tl.blog.common.log.service;

import org.tl.blog.common.base.BaseService;
import org.tl.blog.common.log.entity.SysLog;

public interface SysLogService extends BaseService<SysLog> {

    public void logging(String title,String content,String status);

    public void logging(String title,String content);
}
