package org.tl.blog.common.pojo;

import org.tl.blog.admin.entity.SysUser;

import java.util.Date;
import java.util.List;
import java.util.Map;


public class Client implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private SysUser user;
	/**
	 * 用户IP
	 */
	private String ip;
	/**
	 *登录时间
	 */
	private java.util.Date logindatetime;

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLogindatetime() {
		return logindatetime;
	}

	public void setLogindatetime(Date logindatetime) {
		this.logindatetime = logindatetime;
	}
}
