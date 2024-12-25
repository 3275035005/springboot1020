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
 * 农产品表
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 农产品名称
     */
    private String productName;

    /**
     * 农产品品牌id
     */
    private Integer categoryId;

    /**
     * 农产品标题
     */
    private String productTitle;

    /**
     * 农产品介绍
     */
    private String productIntro;

    /**
     * 封面
     */
    private String productPicture;

    /**
     * 原价
     */
    private Double productPrice;

    /**
     * 现价
     */
    private Double productSellingPrice;

    /**
     * 库存
     */
    private Integer productNum;

    /**
     * 销量
     */
    private Integer productSales;

    /**
     * 发布用户id
     */
    private Integer userId;

    /**
     * 类型(0原价农产品 1打折农产品 2试吃农产品)
     */
    private String type;


    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(exist = false)
    private List<String> imageList;

    /**
     * 类别名称
     */
    @TableField(exist = false)
    private String categoryName;

    /**
     * 贫困户姓名
     */
    @TableField(exist = false)
    private String userName;

}
