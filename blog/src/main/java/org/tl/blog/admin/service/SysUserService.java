package org.tl.blog.admin.service;

import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.common.base.BaseService;

public interface SysUserService extends BaseService<SysUser> {

    public boolean login(SysUser sysUser);

    public boolean register(SysUser sysUser);

    public boolean logout();

}
