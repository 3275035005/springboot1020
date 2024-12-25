package com.cn.product.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.product.entity.*;
import com.cn.product.service.*;
import com.cn.product.utils.page.PageResult;
import com.cn.product.utils.response.R;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.catalina.User;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * 对用户平台提供接口
 */
@RestController
@RequestMapping("/web")
public class UserWebController {

    @Autowired
    private CategoryService categoryService;


    @Autowired
    private OrdersService ordersService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductPictureService productPictureService;

    @Autowired
    private ShoppingcartService shoppingcartService;

    @Autowired
    private UsersService usersService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private InformationService informationService;


    @Autowired
    private ProjectService projectService;

    @Autowired
    private PolicyService policyService;

    @Autowired
    private NoticeService noticeService;

    /**
     * 登录
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
        if(users1.getRole().equals("1")){
            return R.error("账号不存在");
        }
        return R.ok().data("user",users1);
    }


    /**
     * 注册
     * @param users
     * @return
     */
    @PostMapping("register")
    public R register(@RequestBody Users users){
        Users users1 = usersService.getOne(new QueryWrapper<Users>().eq("username", users.getUsername()));
        if(users1 != null){
            return R.error("账号已被注册");
        }
        users.setRole("3");
        usersService.save(users);

        return R.ok("注册成功");
    }

    /**
     * 个人信息查询
     */
    @GetMapping("getUserById/{id}")
    public R getUserById(@PathVariable Integer id){
        Users user = usersService.getById(id);
        return R.ok().data("row", user);
    }

    /**
     * 个人信息修改
     */
    @PostMapping("updateUser")
    public R updateUser(@RequestBody Users user){
        usersService.updateById(user);
        return R.ok();
    }

    /**
     * 查询首页数据
     *      1、政策轮播图 policy
     *      2、通知    notice
     *      3、扶贫资讯 information
     *      4、扶贫项目 project
     *      5、试吃农产品 product
     */
    @GetMapping("getHome")
    public R getHome(){
        Map<String, Object> map = new HashMap<>();
        List<Policy> policy =  policyService.getList();
        List<Information> information =  informationService.getList();
        List<Project> project = projectService.list(new QueryWrapper<Project>().eq("status", "1").isNull("del"));
        List<Product> product =  productService.getList(new Product().setType("1"));

        map.put("banner", project);
        map.put("information", information);
        map.put("project", project);
        map.put("policy", policy);
        map.put("product", product);
        return R.ok().data("data",map);
    }

    /**
     * 查询通知
     * @return
     */
    @GetMapping("getNotice")
    public R getNotice(){
        List<Notice> notice = noticeService.list(new QueryWrapper<Notice>().orderByDesc("create_time"));
        return R.ok().data("list", notice);
    }
    /**
     * 查询扶贫资讯 和 扶贫政策
     * @param type 1扶贫资讯 2扶贫政策
     * @return
     */
    @GetMapping("getInformationAndPolicy/{type}")
    public R getInformationAndPolicy(@PathVariable String type){

        if("1".equals(type)){
            List<Information> informationList =  informationService.getList();
            return R.ok().data("list", informationList);
        }else{
            List<Policy> policyList =  policyService.getList();
            return R.ok().data("list", policyList);
        }
    }

    /**
     * 查询扶贫资讯 和 扶贫政策
     * @param type 1扶贫资讯 2扶贫政策
     * @return
     */
    @GetMapping("getProductListBy/{type}")
    public R getProductListBy(@PathVariable String type){
        List<Product> product =  productService.getList(new Product().setType(type));
        return R.ok().data("list", product);
    }

    /**
     * 查询扶贫资讯详情
     * @param id
     * @return
     */
    @GetMapping("getInformationById/{id}")
    public R getInformationById(@PathVariable Integer id) {
        Information information = informationService.getById(id);
        return R.ok().data("data", information);
    }

    /**
     * 查询扶贫政策详情
     * @param id
     * @return
     */
    @GetMapping("getPolicyById/{id}")
    public R getPolicyById(@PathVariable Integer id) {
        Policy policy = policyService.getById(id);
        return R.ok().data("data", policy);
    }

    /**
     * 查询扶贫项目详情
     * @param id
     * @return
     */
    @GetMapping("getProjectById/{id}")
    public R getProjectById(@PathVariable Integer id) {
        Project project = projectService.getById(id);
        return R.ok().data("data", project);
    }
    /**
     * 查询农产品详情
     * @param id
     * @return
     */
    @GetMapping("getProductById/{id}")
    public R getProductById(@PathVariable Integer id) {
        Product product = productService.getListById(id);


        return R.ok().data("data", product);
    }


    /**
     * 查询我的购物车
     */
    @GetMapping("getShoppingCart/{id}")
    public R getShoppingCart(@PathVariable Integer id){
        List<Shoppingcart> list = shoppingcartService.getShoppingCart(id);
        return R.ok().data("list", list);
    }

    /**
     * 加入购物车
     */
    @PostMapping("addShoppingCart")
    public R addShoppingCart(@RequestBody Shoppingcart shoppingcart){
        Shoppingcart shoppingcartNew = shoppingcartService.getOne(new QueryWrapper<Shoppingcart>()
                .eq("user_id", shoppingcart.getUserId())
                .eq("product_id", shoppingcart.getProductId()));
        Product product = productService.getById(shoppingcart.getProductId());
        if(product.getProductNum() == 0){
            return R.ok("库存不足，无法加入", 10003);
        }
        if(shoppingcartNew == null){
            shoppingcart.setNum(1);
            shoppingcartService.save(shoppingcart);
            Shoppingcart row = shoppingcartService.getShoppingCartById(shoppingcart.getId());
            return R.ok("加入购物车成功", 10001).data("row", row);
        }else{

            shoppingcartNew.setNum(shoppingcartNew.getNum() + 1);


            if(product != null){
                if(shoppingcartNew.getNum() > product.getProductNum()){
                    return R.ok("库存不足，无法加入", 10003);
                }
            }

            shoppingcartService.updateById(shoppingcartNew);
            return R.ok("该农产品已在购物车，数量 +1",10002);

        }
    }

    /**
     * 修改我的购物车
     */
    @PostMapping("updateShoppingCart")
    public R updateShoppingCart(@RequestBody Shoppingcart shoppingcart){
        Shoppingcart shoppingcartNew = shoppingcartService.getOne(new QueryWrapper<Shoppingcart>()
                .eq("user_id", shoppingcart.getUserId())
                .eq("product_id", shoppingcart.getProductId()));

        shoppingcartNew.setNum(shoppingcart.getNum());

        shoppingcartService.updateById(shoppingcartNew);
        return R.ok();
    }
    /**
     * 删除购物车
     */
    @PostMapping("deleteShoppingCart")
    public R deleteShoppingCart(@RequestBody Shoppingcart shoppingcart){
        Shoppingcart shoppingcartNew = shoppingcartService.getOne(new QueryWrapper<Shoppingcart>()
                .eq("user_id", shoppingcart.getUserId())
                .eq("product_id", shoppingcart.getProductId()));

       shoppingcartService.removeById(shoppingcartNew);
        return R.ok();
    }

    /**
     * 下单
     */
    @PostMapping("addOrder/{userId}")
    public R addOrder(@PathVariable Integer userId, @RequestBody List<Shoppingcart> shoppingcarts){
        Users users = usersService.getById(userId);

        // 获取订单号
        Long orderId = System.currentTimeMillis();


        // 生成订单
        for (Shoppingcart shoppingcart : shoppingcarts) {
            if(shoppingcart.isCheck()){
                // 扣减商品库存和加销量
                Product product = productService.getById(shoppingcart.getProductId());
                if(shoppingcart.getNum() > product.getProductNum()){
                    return R.error(product.getProductName()+"库存不足");
                }
                product.setProductNum(product.getProductNum() - shoppingcart.getNum() );
                product.setProductSales(product.getProductSales()+ shoppingcart.getNum() );
                productService.updateById(product);


                Orders orders = new Orders();
                orders.setStatus("1");
                orders.setOrderId(orderId);
                orders.setUserId(userId);
                orders.setAddress(users.getAddress());
                orders.setName(users.getName());
                orders.setPhone(users.getPhone());
                orders.setProductId(shoppingcart.getProductId());
                orders.setProductNum(shoppingcart.getNum());
                orders.setProductPrice(shoppingcart.getPrice());
                ordersService.save(orders);



                shoppingcartService.removeById(shoppingcart.getId());
            }

        }


        return R.ok("购买成功");
    }
    /**
     * 贫困项目删除
     * @return
     */
    @DeleteMapping("projectDeleteById/{id}")
    public R projectDeleteById(@PathVariable Integer id){
        projectService.removeById(id);
        return R.ok();
    }

    /**
     * 贫困户删除
     * @return
     */
    @DeleteMapping("applicationDeleteById/{id}")
    public R applicationDeleteById(@PathVariable Integer id){
        applicationService.removeById(id);
        return R.ok();
    }

    /**
     * 查询我的订单
     */
    @GetMapping("getOrder/{id}")
    public R getOrder(@PathVariable Integer id){
        List<Orders> list = ordersService.getOrder(id);
        return R.ok().data("data", list);
    }



    /**
     * 农产品分页条件查询
     * @param page   当前页码
     * @param limit 每页的大小
     * @param data 封装查询条件数据
     * @return
     */
    @PostMapping("getProductByCategory/{page}/{limit}")
    public R getPageData(
            @PathVariable int page,
            @PathVariable int limit,
            @RequestBody Product data){
        PageResult pageResult = productService.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }


    /**
     * 农产品所有查询
     * @param page   当前页码
     * @param limit 每页的大小
     * @return
     */
    @PostMapping("getAllProduct/{page}/{limit}")
    public R getAllProduct(
            @PathVariable int page,
            @PathVariable int limit){
        PageResult pageResult = productService.pageQuery(page, limit, new Product());
        return R.ok().data("rows",pageResult);
    }

    /**
     * 所有农产品分类查询
     */
    @GetMapping("getCategory")
    public R getCategory(){
        List<Category> list = categoryService.list(new QueryWrapper<Category>().orderByDesc("create_time"));
        return R.ok().data("rows",list);
    }

    /**
     * 我的申请查询
     * @param userId 用户id
     * @param type 1查询贫困户申请 2查询扶贫项目
     * @return
     */
    @GetMapping("getApply/{userId}/{type}")
    public R getApply(@PathVariable Integer userId,@PathVariable String type){
        if("1".equals(type)){
            List<Application> list = applicationService.list(new QueryWrapper<Application>().eq("user_id", userId).orderByDesc("create_time"));
            return R.ok().data("rows", list);
        }else{
            List<Project> list = projectService.list(new QueryWrapper<Project>().eq("user_id", userId).orderByDesc("create_time"));
            return R.ok().data("rows", list);
        }
    }

    /**
     * 贫困户申请
     * @return
     */
    @PostMapping("sendApplication")
    public R getApply(@RequestBody Application application){
        Users user = usersService.getById(application.getUserId());
        if("2".equals(user.getRole())){
            return R.error("已经是贫困户无需申请");
        }
        application.setStatus("0");
        applicationService.save(application);
        return R.ok();
    }

    /**
     * 扶贫项目申请
     * @return
     */
    @PostMapping("sendProject")
    public R sendProject(@RequestBody Project project){
        project.setStatus("0");
        projectService.save(project);
        return R.ok();
    }

    /**
     * 删除订单
     */
    @DeleteMapping("deleteOrder/{orderId}")
    public R deleteOrder(@PathVariable String orderId){
        ordersService.remove(new QueryWrapper<Orders>().eq("order_id", orderId));
        return R.ok("删除成功");
    }

    /**
     * 确认收货
     */
    @PostMapping("sendOrder/{orderId}")
    public R sendOrder(@PathVariable String orderId){
        ordersService.update(new Orders().setStatus("3") ,new QueryWrapper<Orders>().eq("order_id", orderId));
        return R.ok("确认收货成功");
    }

}
