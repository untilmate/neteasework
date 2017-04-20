package cn.com.netease.service;

import cn.com.netease.models.Content;

/**
 * 
 * Content商品表操作
 */
public interface ContentService {
    //添加商品
    void addProduct(Content content);

    //添加商品检验
    int chenkProduct(Content content);

    //根据id查询商品
    Content findProduct(int id);

    //根据id修改商品
    void editProduct(Content content);

    //根据id删除商品
    Boolean delProduct(int id);

    //查询商品价格
    Double productPrice(int id);

}
