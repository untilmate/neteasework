package cn.com.netease.service.impl;

import cn.com.netease.dao.ProductDao;
import cn.com.netease.models.Product;
import cn.com.netease.service.ProductService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Resource
    private ProductDao productDao;

    public List<Product> productList() {
        return productDao.productList();
    }

    public Product showProduct(int id) {
        return productDao.showProduct(id);
    }
}
