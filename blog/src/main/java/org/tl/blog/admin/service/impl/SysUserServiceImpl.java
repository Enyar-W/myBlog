package org.tl.blog.admin.service.impl;

import org.springframework.stereotype.Service;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.admin.mapper.SysUserMapper;
import org.tl.blog.admin.service.SysUserService;
import org.tl.blog.common.base.BaseServiceImpl;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {
}
