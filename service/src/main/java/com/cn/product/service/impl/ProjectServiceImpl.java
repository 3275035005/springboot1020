package com.cn.product.service.impl;

import com.cn.product.entity.Project;
import com.cn.product.entity.Project;
import com.cn.product.mapper.ProjectMapper;
import com.cn.product.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 扶贫项目 服务实现类
 * </p>
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

    @Override
    public PageResult pageQuery(int page, int limit, Project data) {
        PageHelper.startPage(page, limit);
        List<Project> queryList = baseMapper.pageQuery(data);
        PageInfo<Project> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
