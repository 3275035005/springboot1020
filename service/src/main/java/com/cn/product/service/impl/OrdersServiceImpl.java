package com.cn.product.service.impl;

import com.cn.product.entity.Orders;
import com.cn.product.entity.Orders;
import com.cn.product.mapper.OrdersMapper;
import com.cn.product.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {

    @Override
    public PageResult pageQuery(int page, int limit, Orders data) {
        PageHelper.startPage(page, limit);
        List<Orders> queryList = baseMapper.pageQuery(data);
        PageInfo<Orders> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<Orders> getOrder(Integer id) {
        return baseMapper.getOrder(id);
    }
}
