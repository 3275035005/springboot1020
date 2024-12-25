package com.cn.product.service.impl;

import com.cn.product.entity.Users;
import com.cn.product.entity.Users;
import com.cn.product.mapper.UsersMapper;
import com.cn.product.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

    @Override
    public PageResult pageQuery(int page, int limit, Users data) {
        PageHelper.startPage(page, limit);
        List<Users> queryList = baseMapper.pageQuery(data);
        PageInfo<Users> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }
}
