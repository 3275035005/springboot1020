package com.cn.product.service;

import com.cn.product.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.product.utils.page.PageResult;

/**
 * <p>
 * 公告信息 服务类
 * </p>
 */
public interface NoticeService extends IService<Notice> {

    PageResult pageQuery(int page, int limit, Notice data);

}
