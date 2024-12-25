package com.cn.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cn.product.entity.Product;
import com.cn.product.entity.Product;
import com.cn.product.entity.ProductPicture;
import com.cn.product.mapper.ProductMapper;
import com.cn.product.mapper.ProductPictureMapper;
import com.cn.product.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cn.product.utils.page.PageResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 农产品表 服务实现类
 * </p>
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductPictureMapper productPictureMapper;

    @Override
    public PageResult pageQuery(int page, int limit, Product data) {
        PageHelper.startPage(page, limit);
        List<Product> queryList = baseMapper.pageQuery(data);
        for (Product product : queryList) {
            List<String> list = new ArrayList<>();
            List<ProductPicture> productPictures = productPictureMapper.selectList(new QueryWrapper<ProductPicture>().eq("product_id", product.getId()));
            for (ProductPicture productPicture : productPictures) {
                list.add(productPicture.getProductPicture());
            }
            product.setImageList(list);

        }
        PageInfo<Product> pageInfo = new PageInfo<>(queryList);
        return new PageResult<>(pageInfo.getTotal(), queryList);
    }

    @Override
    public List<Product> getList(Product product) {
        LambdaQueryWrapper<Product> qw = new LambdaQueryWrapper<>();
        if(StringUtils.isNotEmpty(product.getType())){
            qw.eq(Product::getType, product.getType());
        }

        if(StringUtils.isNotEmpty(product.getProductName())){
            qw.eq(Product::getProductName, product.getProductName());
        }

        return baseMapper.selectList(qw);
    }

    @Override
    public Product getListById(Integer id) {

        Product product = baseMapper.selectById(id);

        List<ProductPicture> productPictures = productPictureMapper.selectList(new QueryWrapper<ProductPicture>().eq("product_id", product.getId()));
        List<String> list = new ArrayList<>();
        for (ProductPicture productPicture : productPictures) {
            list.add(productPicture.getProductPicture());
        }
        product.setImageList(list);
        return product;
    }
}
