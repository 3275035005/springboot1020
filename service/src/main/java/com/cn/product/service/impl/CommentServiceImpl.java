package com.cn.product.service.impl;

import com.cn.product.entity.Comment;
import com.cn.product.entity.Comment;
import com.cn.product.mapper.CommentMapper;
import com.cn.product.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 扶贫项目评论表 服务实现类
 * </p>
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {

    @Override
    public PageResult pageQuery(int page, int limit, Comment data) {
        PageHelper.startPage(page, limit);
        List<Comment> queryList = baseMapper.pageQuery(data);
        PageInfo<Comment> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
