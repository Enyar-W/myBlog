package org.tl.blog.admin.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
/**
*
*  @author TerryLam
*/
@Data
public class BlogPostTagRel implements Serializable {

    private static final long serialVersionUID = 1545466797355L;


    /**
    * 主键
    * 主键Id
    * isNullAble:0
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
    * 文章id
    * isNullAble:0
    */
    private Integer postId;

    /**
    * 标签id
    * isNullAble:0
    */
    private Integer tagId;



}
