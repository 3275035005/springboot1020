package com.cn.product.service;

import com.cn.product.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cn.product.utils.page.PageResult;

/**
 * <p>
 * 扶贫项目评论表 服务类
 * </p>
 */
public interface CommentService extends IService<Comment> {

    PageResult pageQuery(int page, int limit, Comment data);
}
