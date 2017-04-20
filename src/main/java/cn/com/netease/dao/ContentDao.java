package cn.com.netease.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import cn.com.netease.models.Content;

/**
 * 
 *商品内容类数据库操作
 */
public interface ContentDao {
    //存储商品信息
    @Insert("insert into `content`(`price`,`title`,`icon`,`abstract`,`text`) values (#{price},#{title},#{icon},#{abst},#{text})")
    public void addProduct( Content content);
    //查询商品信息
    @Select("select * from `content` where id = #{id}")
    public Content findProduct(int id);
    //查询商品信息
    @Select("select id from `content` where title = #{title} and price = #{price} and icon = #{icon} and abstract = #{abst} and text = #{text}")
    public int checkProduct(Content content);
    //修改商品信息
    @Update("update `content` set title = #{title} , price = #{price} , icon = #{icon} , abstract = #{abst} , text = #{text} where id = #{id}")
    public void editProduct(Content content);
    //根据id删除商品
    @Delete("delete from `content` where id = #{id}")
    public  int delProduct(int id);
    //查询商品价格
    @Select("select price from `content` where id = #{id}")
    public Double productPrice(int id);
}
