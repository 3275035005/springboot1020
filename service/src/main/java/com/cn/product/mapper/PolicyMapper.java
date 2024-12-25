package com.cn.product.mapper;

import com.cn.product.entity.Policy;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 扶贫政策 Mapper 接口
 * </p>
 */
public interface PolicyMapper extends BaseMapper<Policy> {

    List<Policy> pageQuery(Policy data);

}
