package com.cn.product.service;

import com.cn.product.entity.Application;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.product.utils.page.PageResult;

/**
 * <p>
 * 贫困户申请 服务类
 * </p>
 */
public interface ApplicationService extends IService<Application> {

    PageResult pageQuery(int page, int limit, Application data);

}
