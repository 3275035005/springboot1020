package com.cn.product.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.product.entity.Product;
import com.cn.product.entity.ProductPicture;
import com.cn.product.service.ProductPictureService;
import com.cn.product.service.ProductService;
import com.cn.product.utils.page.PageResult;
import com.cn.product.utils.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 农产品表 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/product")
public class ProductController {


    @Autowired
    private ProductService service;

    @Autowired
    private ProductPictureService productPictureService;
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
            @RequestBody Product data){
        PageResult pageResult = service.pageQuery(page, limit, data);
        return R.ok().data("rows",pageResult);
    }

    /**
     * 修改操作
     * @param data
     * @return
     */
    @PutMapping("update")
    public R update(@RequestBody Product data){
        productPictureService.remove(new QueryWrapper<ProductPicture>().eq("product_id", data.getId()));

        for (String image : data.getImageList()) {
            ProductPicture productPicture = new ProductPicture();
            productPicture.setProductId(data.getId());
            productPicture.setProductPicture(image);
            productPictureService.save(productPicture);
        }

        service.updateById(data);
        return R.ok();
    }

    /**
     * 新增操作
     * @param data
     * @return
     */
    @PostMapping("insert")
    public R insert(@RequestBody Product data){
        data.setProductSales(0);
        service.save(data);

        for (String image : data.getImageList()) {
            ProductPicture productPicture = new ProductPicture();
            productPicture.setProductId(data.getId());
            productPicture.setProductPicture(image);
            productPictureService.save(productPicture);
        }
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
}

