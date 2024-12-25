package com.cn.product.mapper;

import com.cn.product.entity.Users;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 */
public interface UsersMapper extends BaseMapper<Users> {

    List<Users> pageQuery(Users data);
}
