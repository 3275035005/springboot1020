package com.cn.product.mapper;

import com.cn.product.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 扶贫项目 Mapper 接口
 * </p>
 */
public interface ProjectMapper extends BaseMapper<Project> {

    List<Project> pageQuery(Project data);

}
