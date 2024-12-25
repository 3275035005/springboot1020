package com.cn.product.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 订单表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单号
     */
    private Long orderId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 农产品id
     */
    private Integer productId;

    /**
     * 数量
     */
    private Integer productNum;

    /**
     * 单价
     */
    private Double productPrice;

    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人手机号
     */
    private String phone;

    /**
     * 收货人地址
     */
    private String address;

    /**
     * 订单状态(1待收货 2 已发货 3 订单完成)
     */
    private String status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 总价格
     */
    @TableField(exist = false)
    private Double totalPrice;


    /**
     * 下单用户
     */
    @TableField(exist = false)
    private String userName;

    @TableField(exist = false)
    private String productName;

    @TableField(exist = false)
    private String productPicture;

    @TableField(exist = false)
    List<Orders> ordersList;
}
