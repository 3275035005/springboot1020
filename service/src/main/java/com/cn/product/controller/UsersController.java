package com.cn.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.product.entity.Users;
import com.cn.product.service.InformationService;
import com.cn.product.service.ProductService;
import com.cn.product.service.UsersService;
import com.cn.product.utils.page.PageResult;
import com.cn.product.utils.response.R;
import com.cn.product.utils.utils.AceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UsersController {


    @Autowired
    private UsersService usersService;


    @Autowired
    private ProductService productService;

    @Autowired
    private InformationService informationService;


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
            @RequestBody Users data){
        PageResult pageResult = usersService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }

    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody Users data){
        usersService.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody  Users data){
        usersService.save(data);
        return R.ok();
    }

    /**
     * 删除操作
     * @param id
     * @return
     */
    @DeleteMapping("deleteById/{id}")
    public R deleteById(@PathVariable Integer id){
        usersService.removeById(id);
        return R.ok();
    }


    /**
     * 平台登录
     * @param users
     * @return
     */
    @PostMapping("login")
    public R login(@RequestBody Users users){

        QueryWrapper<Users> qw = new QueryWrapper<>();
        LambdaQueryWrapper<Users> lambda = qw.lambda();
        lambda.eq(Users::getUsername, users.getUsername());
        Users users1 = usersService.getOne(qw);
        if(users1 == null){
            return R.error("账号不存在");
        }
        if(!users.getPassword().equals(users1.getPassword())){
            return R.error("密码不正确");
        }
        if(!users1.getRole().equals("1") && !users1.getRole().equals("2")){
            return R.error("账号不存在");
        }
        return R.ok().data("token",users1.getId());
    }
    /**
     * 重置密码 默认密码666666
     * @param id
     * @return
     */
    @PostMapping("resetPassword/{id}")
    public R resetPassword(@PathVariable String id){
        Users pUser = usersService.getById(id);
        pUser.setPassword("666666");
        usersService.updateById(pUser);
        return R.ok();
    }


    /**
     * 修改密码
     */
    @PostMapping("updatePassword")
    public R updatePassword(@RequestBody Users data){
        Users pUser = usersService.getById(data.getId());



        if(!data.getPassword().equals(pUser.getPassword())){
            return R.error("旧密码不正确");
        }
        // 更新密码
        pUser.setPassword(data.getPassword());
        usersService.updateById(pUser);
        return R.ok();
    }

    /**
     * 通过用户id获取信息
     * @return
     */
    @GetMapping("info")
    public R info(String token){
        Users users = usersService.getById(token);
        if("1".equals(users.getRole())){
            users.setRoles(new String[]{"admin"});
        } else  if("2".equals(users.getRole())){
            users.setRoles(new String[]{"poverty"});
        }

        return R.ok().data("data",users);
    }

    /**
     * 获取所有用户
     * @return
     */
    @GetMapping("getUserAll")
    public R getUserAll(){
        QueryWrapper<Users> qw = new QueryWrapper<>();
        LambdaQueryWrapper<Users> lambda = qw.lambda();
        lambda.in(Users::getRole, "2","3");
        List<Users> list = usersService.list(qw);
        return R.ok().data("data",list);
    }
}

