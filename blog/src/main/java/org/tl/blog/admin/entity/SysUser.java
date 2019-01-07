package org.tl.blog.admin.entity;

import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
*
*  @author TerryLam
*/

@Data
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1545466807602L;


    /**
    * 主键
    * 用户id
    * isNullAble:0
    */
    @Id
    private Integer userId;

    /**
    * 登录名
    * isNullAble:1
    */
    private String username;

    /**
    * 密码
    * isNullAble:1
    */
    private String passoword;

    /**
    * 用户名
    * isNullAble:1
    */
    private String showName;

    /**
    * 登录可用
    * isNullAble:1
    */
    private String loginEnable;

    /**
    * 错误次数
    * isNullAble:1
    */
    private Integer loginError;

    /**
    * 最后登录
    * isNullAble:1
    */
    private Date loginLast;

    /**
    * 邮箱
    * isNullAble:1
    */
    private String email;

    /**
    * 创建时间
    * isNullAble:1
    */
    private Date createAt;

    /**
    * 创建人
    * isNullAble:1
    */
    private Integer createBy;

    /**
    * 修改时间
    * isNullAble:1
    */
    private Date updateAt;

    /**
    * 修改人
    * isNullAble:1
    */
    private Integer updateBy;


}
