package com.cn.product.mapper;

import com.cn.product.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 公告信息 Mapper 接口
 * </p>
 */
public interface NoticeMapper extends BaseMapper<Notice> {

    List<Notice> pageQuery(Notice data);

}
