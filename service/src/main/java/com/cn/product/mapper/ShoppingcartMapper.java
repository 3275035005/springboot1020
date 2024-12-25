package com.cn.product.mapper;

import com.cn.product.entity.Shoppingcart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 */
public interface ShoppingcartMapper extends BaseMapper<Shoppingcart> {

    List<Shoppingcart> getShoppingCart(@Param("id") Integer id);

    Shoppingcart getShoppingCartById(@Param("id")Integer id);
}
