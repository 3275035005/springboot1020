package com.cn.product.service;

import com.cn.product.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.product.utils.page.PageResult;

/**
 * <p>
 * 农产品品牌 服务类
 * </p>
 */
public interface CategoryService extends IService<Category> {

    PageResult pageQuery(int page, int limit, Category data);

}
