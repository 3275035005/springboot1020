package com.cn.product.service;

import com.cn.product.entity.Shoppingcart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 购物车 服务类
 * </p>
 */
public interface ShoppingcartService extends IService<Shoppingcart> {

    /**
     * 查询我的购物车
     * @param id
     * @return
     */
    List<Shoppingcart> getShoppingCart(Integer id);

    Shoppingcart getShoppingCartById(Integer id);
}
