package com.cn.product.mapper;

import com.cn.product.entity.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 农产品表 Mapper 接口
 * </p>
 */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> pageQuery(Product data);

}
