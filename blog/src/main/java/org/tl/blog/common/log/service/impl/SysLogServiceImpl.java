package org.tl.blog.common.log.service.impl;

import org.springframework.stereotype.Service;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.common.base.BaseServiceImpl;
import org.tl.blog.common.log.entity.SysLog;
import org.tl.blog.common.log.mapper.SysLogMapper;
import org.tl.blog.common.log.service.SysLogService;
import org.tl.blog.common.pojo.Client;
import org.tl.blog.common.utils.ClientManager;
import org.tl.blog.common.utils.ContextHolderUtils;

import javax.servlet.http.HttpSession;

@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    /**
     * 插入操作日志
     *
     * @param title
     * @param content
     * @param status
     */
    @Override
    public void logging(String title, String content, String status) {
        HttpSession session = ContextHolderUtils.getSession();
        ClientManager clientManager = ClientManager.getInstance();
        Client client = clientManager.getClient(session.getId());
        if (session.getId() != null && client != null) {
            SysUser user = client.getUser();
            SysLog sysLog = new SysLog();
            sysLog.setLogTitle(title);
            sysLog.setLogContent(content);
            sysLog.setLogIp(client.getIp());
            sysLog.setStatus(status);
            sysLog.setCreateBy(user.getUserId());
            insert(sysLog);
        }
    }

    @Override
    public void logging(String title, String content) {
        logging(title, content, "200");
    }
}
