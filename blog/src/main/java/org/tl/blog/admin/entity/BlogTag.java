package org.tl.blog.admin.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
*
*  @author TerryLam
*/
@Data
public class BlogTag implements Serializable {

    private static final long serialVersionUID = 1545466800205L;


    /**
    * 主键
    * 主键Id
    * isNullAble:0
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer tagId;

    /**
    * 标签名
    * isNullAble:1
    */
    private String tagName;

    /**
    * 标签url
    * isNullAble:1
    */
    private String tagUrl;

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
    private java.time.LocalDateTime updateAt;

    /**
    * 修改人
    * isNullAble:1
    */
    private Integer updateBy;



}
