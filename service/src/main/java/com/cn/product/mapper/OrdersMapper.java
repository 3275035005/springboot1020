package com.cn.product.mapper;

import com.cn.product.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    List<Orders> pageQuery(Orders data);

    List<Orders> getOrder(@Param("id") Integer id);
}
