package com.cn.product.service.impl;

import com.cn.product.entity.Shoppingcart;
import com.cn.product.mapper.ShoppingcartMapper;
import com.cn.product.service.ShoppingcartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 */
@Service
public class ShoppingcartServiceImpl extends ServiceImpl<ShoppingcartMapper, Shoppingcart> implements ShoppingcartService {


    @Override
    public List<Shoppingcart> getShoppingCart(Integer id) {
        return baseMapper.getShoppingCart(id);
    }

    @Override
    public Shoppingcart getShoppingCartById(Integer id) {
        return baseMapper.getShoppingCartById(id);
    }
}
