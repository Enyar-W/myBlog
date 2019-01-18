package org.tl.blog.admin.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.*;

/**
*
*  @author TerryLam
*/
@Data
public class BlogMenu implements Serializable {

    private static final long serialVersionUID = 1545466802855L;


    /**
    * 主键
    * 主键Id
    * isNullAble:0
    */
    @Id
    @KeySql(useGeneratedKeys = true, order = ORDER.AFTER)
    private Integer menuId;

    /**
    * 菜单icon
    * isNullAble:1
    */
    private String menuIcon;

    /**
    * 菜单名
    * isNullAble:1
    */
    private String menuName;

    /**
    * 菜单排序
    * isNullAble:1
    */
    private Integer menuSort;

    /**
    * 菜单目标
    * isNullAble:1
    */
    private String menuTarget;

    /**
    * 访问链接
    * isNullAble:1
    */
    private String menuUrl;

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
