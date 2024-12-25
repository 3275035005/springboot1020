package com.cn.product.service.impl;

import com.cn.product.entity.Category;
import com.cn.product.entity.Category;
import com.cn.product.mapper.CategoryMapper;
import com.cn.product.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 农产品品牌 服务实现类
 * </p>
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Override
    public PageResult pageQuery(int page, int limit, Category data) {
        PageHelper.startPage(page, limit);
        List<Category> queryList = baseMapper.pageQuery(data);
        PageInfo<Category> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
