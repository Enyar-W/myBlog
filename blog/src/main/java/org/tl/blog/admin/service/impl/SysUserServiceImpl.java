package org.tl.blog.admin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.admin.mapper.SysUserMapper;
import org.tl.blog.admin.service.SysUserService;
import org.tl.blog.common.base.BaseServiceImpl;
import org.tl.blog.common.pojo.Client;
import org.tl.blog.common.utils.ClientManager;
import org.tl.blog.common.utils.ContextHolderUtils;

import javax.servlet.http.HttpSession;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    /**
     * 登录操作
     * @return boolean
     */
    @Override
    public boolean login(SysUser sysUser) {
        sysUser.setPassoword(DigestUtils.md5DigestAsHex(sysUser.getPassoword().getBytes()));
        SysUser sysUserForDB = baseMapper.userlLogin(sysUser);
        if (sysUserForDB != null) {
            // 记录sessionId ，sessionId为key，client为value
            // 服务端存储当前用户信息
            HttpSession session = ContextHolderUtils.getSession();
            ClientManager clientManager = ClientManager.getInstance();
            Client client = new Client();
            client.setUser(sysUserForDB);
            clientManager.addClinet(session.getId(),client);
            return true;
        }
        return false;
    }

    /**
     * 注册
     * @return boolean
     */
    @Override
    public boolean register(SysUser sysUser) {
        sysUser.setPassoword(DigestUtils.md5DigestAsHex(sysUser.getPassoword().getBytes()));
        insert(sysUser);
        return true;
    }

    /**
     * 登出操作 删除缓存
     * @return boolean
     */
    @Override
    public boolean logout(){
        HttpSession session = ContextHolderUtils.getSession();
        ClientManager clientManager = ClientManager.getInstance();
        Client client = clientManager.getClient(session.getId());
        if(client!=null){
            clientManager.removeClinet(session.getId());
            return true;
        }
       return false;
    }
}
