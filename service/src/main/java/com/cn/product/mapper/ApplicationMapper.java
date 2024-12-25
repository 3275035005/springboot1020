package com.cn.product.mapper;

import com.cn.product.entity.Application;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 贫困户申请 Mapper 接口
 * </p>
 */
public interface ApplicationMapper extends BaseMapper<Application> {

    List<Application> pageQuery(Application data);
}
