package com.cn.product.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 扶贫项目
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Project implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 申请用户id
     */
    private Integer userId;

    /**
     * 封面
     */
    private String image;

    /**
     * 状态（0审核中 1申请成功 2拒绝）
     */
    private String status;

    /**
     * 拒绝理由
     */
    private String reason;

    /**
     * 资料
     */
    private String materials;

    /**
     * 删除标识 1代表管理员已删除
     */
    private String del;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 用户姓名
     */
    @TableField(exist = false)
    private String userName;

}
