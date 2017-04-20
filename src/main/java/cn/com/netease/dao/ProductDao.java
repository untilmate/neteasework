package cn.com.netease.dao;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.com.netease.models.Product;

import java.util.List;

/**
 * 
 * 展示页
 */
public interface ProductDao {
    //查询商品信息
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="title",column="title"),
            @Result(property="image",column="icon"),
            @Result(property="price",column="price"),
            @Result(property="isBuy",column="buy"),
            @Result(property="isSell",column="buy")
    })
    @Select("select content.id, content.title, content.icon, content.price,trx.buy,trx.buy from `content` left join trx on trx.contentId = content.id")
    public List<Product> productList();
    //Show展示 根据ID查询
    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="summary",column="abstract"),
            @Result(property="title",column="title"),
            @Result(property="image",column="icon"),
            @Result(property="detail",column="text"),
            @Result(property="price",column="price")
    })
    @Select("select content.id, content.title, content.abstract, content.icon, content.price, content.text from `content` where id = #{id}")
    public Product showProduct(int id);
}
