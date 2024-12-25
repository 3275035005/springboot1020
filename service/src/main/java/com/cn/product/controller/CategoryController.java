package com.cn.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.product.entity.Category;
import com.cn.product.service.CategoryService;
import com.cn.product.utils.page.PageResult;
import com.cn.product.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 农产品品牌 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService service;

    /**
     * 分页条件查询
     * @param page   当前页码
     * @param limit 每页的大小
     * @param data 封装查询条件数据
     * @return
     */
    @PostMapping("pageQuery/{page}/{limit}")
    public R getPageData(
            @PathVariable int page,
            @PathVariable int limit,
            @RequestBody Category data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }

    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody Category data){
        service.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody Category data){
        service.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R delete(@PathVariable Integer id){
        service.removeById(id);
        return R.ok();
    }


    /**
     * 查询所有分类
     * @return
     */
    @GetMapping("getCategoryAll")
    public R getCategoryAll(){
        List<Category> list = service.list(new QueryWrapper<Category>().orderByDesc("create_time"));
        return R.ok().data("data", list);
    }
}

