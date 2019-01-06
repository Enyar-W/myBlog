package org.tl.blog.admin.entity;
import lombok.Data;

import java.io.Serializable;
import java.util.*;

/**
*
*  @author TerryLam
*/
@Data
public class BlogCategory implements Serializable {

    private static final long serialVersionUID = 1545466737803L;


    /**
    * 主键
    * 主键Id
    * isNullAble:0
    */
    private Integer cateId;

    /**
    * 类别排序
    * isNullAble:1
    */
    private Integer cateDesc;

    /**
    * 类别名
    * isNullAble:1
    */
    private String cateName;

    /**
    * 类别url
    * isNullAble:1
    */
    private String cateUrl;

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
