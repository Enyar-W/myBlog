package org.tl.blog.admin.entity;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
*
*  @author TerryLam
*/
@Data
public class BlogPost implements Serializable {

    private static final long serialVersionUID = 1545466759751L;


    /**
    * 主键
    * 主键Id
    * isNullAble:0
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer postId;

    /**
    * 允许评论
    * isNullAble:1
    */
    private Integer allowComment;

    /**
    * 文章内容
    * isNullAble:1
    */
    private String postContent;

    /**
    * 文章内容md
    * isNullAble:1
    */
    private String postContentMd;

    /**
    * 发布时间
    * isNullAble:1
    */
    private java.time.LocalDateTime postDate;

    /**
    * 发布状态
    * isNullAble:1
    */
    private Integer postStatus;

    /**
    * 概要
    * isNullAble:1
    */
    private String postSummary;

    /**
    * 缩略图
    * isNullAble:1
    */
    private String postThumbnail;

    /**
    * 标题
    * isNullAble:1
    */
    private String postTitle;

    /**
    * 类型
    * isNullAble:1
    */
    private String postType;

    /**
    * 文章url
    * isNullAble:1
    */
    private String postUrl;

    /**
    * 浏览数
    * isNullAble:1
    */
    private Integer postViews;

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
