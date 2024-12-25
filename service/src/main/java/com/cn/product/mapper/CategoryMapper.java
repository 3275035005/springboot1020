package com.cn.product.mapper;

import com.cn.product.entity.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 农产品品牌 Mapper 接口
 * </p>
 */
public interface CategoryMapper extends BaseMapper<Category> {

    List<Category> pageQuery(Category data);

}
