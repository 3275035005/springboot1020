package com.cn.product.mapper;

import com.cn.product.entity.Information;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 扶贫资讯 Mapper 接口
 * </p>
 */
public interface InformationMapper extends BaseMapper<Information> {

    List<Information> pageQuery(Information data);

}
