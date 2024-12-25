package com.cn.product.controller;


import com.cn.product.entity.Application;
import com.cn.product.entity.Project;
import com.cn.product.service.ProjectService;
import com.cn.product.utils.page.PageResult;
import com.cn.product.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 扶贫项目 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/project")
public class ProjectController {


    @Autowired
    private ProjectService service;

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
            @RequestBody Project data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }

    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody Project data){
        service.updateById(data);
        return R.ok();
    }


    /**
     * 审核
     * @param data
     * @return
     */
    @PutMapping("audit")
    public R audit(@RequestBody Project data){
        service.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody Project data){
        service.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R delete(@PathVariable Integer id){
        Project project = service.getById(id);
        project.setDel("1");
        service.updateById(project);
        return R.ok();
    }
}

