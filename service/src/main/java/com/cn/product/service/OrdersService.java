package com.cn.product.service;

import com.cn.product.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.product.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 */
public interface OrdersService extends IService<Orders> {

    PageResult pageQuery(int page, int limit, Orders data);

    List<Orders> getOrder(Integer id);

}
