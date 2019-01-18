package org.tl.blog.admin.entity;
import lombok.Data;
import tk.mybatis.mapper.annotation.KeySql;
import tk.mybatis.mapper.code.ORDER;

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
public class BlogCategoryPostRel implements Serializable {

    private static final long serialVersionUID = 1545466756392L;


    /**
    * 主键
    * 主键Id
    * isNullAble:0
    */
    @Id
    @KeySql(useGeneratedKeys = true, order = ORDER.AFTER)
    private Integer relId;

    /**
    * 文章id
    * isNullAble:0
    */
    private Integer postId;

    /**
    * 类别id
    * isNullAble:0
    */
    private Integer cateId;


}
