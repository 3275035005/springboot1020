package com.cn.product.service;

import com.cn.product.entity.Policy;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.product.utils.page.PageResult;

import java.util.List;

/**
 * <p>
 * 扶贫政策 服务类
 * </p>
 */
public interface PolicyService extends IService<Policy> {

    PageResult pageQuery(int page, int limit, Policy data);

    List<Policy> getList();


}
