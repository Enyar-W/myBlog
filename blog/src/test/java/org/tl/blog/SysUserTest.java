package org.tl.blog;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.admin.service.SysUserService;

public class SysUserTest extends BlogApplicationTests {

    @Autowired
    private SysUserService sysUserService;

    @Test
    public void testRegister(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("terry");
        sysUser.setPassoword("123456");
        sysUserService.register(sysUser);
        System.out.println(sysUser.getUserId());
    }

    @Test
    public void testLogin(){
        SysUser sysUser = new SysUser();
        sysUser.setUsername("terry");
        sysUser.setPassoword("123456");
        boolean login = sysUserService.login(sysUser);
        System.out.println(login);

    }
}
