package com.cn.product.service;

import com.cn.product.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.product.utils.page.PageResult;

/**
 * <p>
 * 扶贫项目 服务类
 * </p>
 */
public interface ProjectService extends IService<Project> {

    PageResult pageQuery(int page, int limit, Project data);
}
