package cn.com.netease.service;

import java.util.List;

import cn.com.netease.models.Product;

/**
 * 
 * 商品操作
 */

public interface ProductService {
    //查询商品List
    List<Product> productList();
    //show页面，根据id查询商品
    Product showProduct(int id);
}
