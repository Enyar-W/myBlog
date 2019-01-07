package org.tl.blog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tl.blog.admin.entity.SysUser;
import org.tl.blog.admin.mapper.SysUserMapper;
import org.tl.blog.admin.service.SysUserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={BlogApplication.class})
public class BlogApplicationTests {

	@Autowired
	//SysUserMapper sysUserMapper;
	SysUserService sysUserService;


	@Test
	public void testBaseWired() {
		SysUser sysUser = sysUserService.findById(1);
		System.out.println(sysUser.getUserId());

	}


}