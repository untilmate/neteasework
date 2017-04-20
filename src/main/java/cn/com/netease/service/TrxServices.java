package cn.com.netease.service;

import java.util.List;

import cn.com.netease.models.Buy;
import cn.com.netease.models.Product;
import cn.com.netease.models.Trx;

/**
 * 
 * trx表
 */
public interface TrxServices {
    //购买商品
    int buyProduct(Trx trx);
    //show页面
    public Product show(Trx trx);
    //账务页面
    public List<Buy> account(int personId);
}
