package org.tl.blog.admin.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.*;

/**
*
*  @author TerryLam
*/
@Data
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1545466805337L;


    /**
    * 主键
    * 主键Id
    * isNullAble:0
    */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer logId;

    /**
    * 日志标题
    * isNullAble:1
    */
    private String logTitle;

    /**
    * 访问ip
    * isNullAble:1
    */
    private String logIp;

    /**
    * 日志详情
    * isNullAble:1
    */
    private String logContent;

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
