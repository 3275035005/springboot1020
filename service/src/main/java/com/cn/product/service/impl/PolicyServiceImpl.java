package com.cn.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.product.entity.Policy;
import com.cn.product.entity.Policy;
import com.cn.product.mapper.PolicyMapper;
import com.cn.product.service.PolicyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 扶贫政策 服务实现类
 * </p>
 */
@Service
public class PolicyServiceImpl extends ServiceImpl<PolicyMapper, Policy> implements PolicyService {

    @Override
    public PageResult pageQuery(int page, int limit, Policy data) {
        PageHelper.startPage(page, limit);
        List<Policy> queryList = baseMapper.pageQuery(data);
        PageInfo<Policy> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<Policy> getList() {
        return baseMapper.selectList(new QueryWrapper<Policy>().orderByDesc("create_time"));

    }
}
