package com.cn.product.mapper;

import com.cn.product.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 扶贫项目评论表 Mapper 接口
 * </p>
 */
public interface CommentMapper extends BaseMapper<Comment> {

    List<Comment> pageQuery(Comment data);

}
