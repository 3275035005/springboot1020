package com.cn.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.product.entity.Information;
import com.cn.product.entity.Information;
import com.cn.product.mapper.InformationMapper;
import com.cn.product.service.InformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 扶贫资讯 服务实现类
 * </p>
 */
@Service
public class InformationServiceImpl extends ServiceImpl<InformationMapper, Information> implements InformationService {

    @Override
    public PageResult pageQuery(int page, int limit, Information data) {
        PageHelper.startPage(page, limit);
        List<Information> queryList = baseMapper.pageQuery(data);
        PageInfo<Information> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<Information> getList() {

        return baseMapper.selectList(new QueryWrapper<Information>().orderByDesc("create_time"));
    }
}
