package com.cn.product.service.impl;

import com.cn.product.entity.Application;
import com.cn.product.mapper.ApplicationMapper;
import com.cn.product.service.ApplicationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 贫困户申请 服务实现类
 * </p>
 */
@Service
public class ApplicationServiceImpl extends ServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Override
    public PageResult pageQuery(int page, int limit, Application data) {
        PageHelper.startPage(page, limit);
        List<Application> queryList = baseMapper.pageQuery(data);
        PageInfo<Application> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
