package com.cn.product.controller;


import com.cn.product.entity.Application;
import com.cn.product.entity.Users;
import com.cn.product.service.ApplicationService;
import com.cn.product.service.UsersService;
import com.cn.product.utils.page.PageResult;
import com.cn.product.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 贫困户申请 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/application")
public class ApplicationController {


    @Autowired
    private ApplicationService service;

    @Autowired
    private UsersService usersService;

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
            @RequestBody Application data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }

    /**
     * 审核
     * @param data
     * @return
     */
    @PutMapping("audit")
    public R audit(@RequestBody Application data){

        // 通过的状态变更角色
        if("1".equals(data.getStatus())){
            Users users = usersService.getById(data.getUserId());
            users.setRole("2");
            usersService.updateById(users);
        }
        service.updateById(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R delete(@PathVariable Integer id){
        Application application = service.getById(id);
        application.setDel("1");
        service.updateById(application);
        return R.ok();
    }
}

