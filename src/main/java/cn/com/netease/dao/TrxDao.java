package cn.com.netease.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import cn.com.netease.models.Buy;
import cn.com.netease.models.Product;
import cn.com.netease.models.Trx;

import java.util.List;

/**
 *
 * trx表操作
 */
public interface TrxDao {
    //存储交易信息
    @Insert("insert into `trx`(`price`,`contentId`,`personId`,`num`,`time`) values (#{price},#{contentId},#{personId},#{num},#{time})")
    public int buyProduct(Trx trx);
    //查询商品是否售出
    @Results({
            @Result(property="isSell",column="buy"),
            @Result(property="saleNum",column="num")
    })
    @Select("select buy,num from `trx` where contentId = #{contentId}")
    public Product sale(Trx trx);
    //查询用户是否购买商品
    @Results({
            @Result(property="buyNum",column="num"),
            @Result(property="isBuy",column="buy"),
            @Result(property="buyPrice",column="price")
    })
    @Select("select buy,num, price from `trx` where contentId = #{contentId} and personId= #{personId}")
    public Product buy(Trx trx);
    //账单页
    @Results({
            @Result(property="buyPrice",column="price"),
            @Result(property="buyTime",column="time"),
            @Result(property="title",column="title"),
            @Result(property="image",column="icon"),
            @Result(property="buyNum",column="num"),
            @Result(property="id",column="id")
    })
    @Select("select trx.price,trx.time,trx.num,content.id,content.title,content.icon from trx left join content on content.id = trx.contentId where personId = #{personId};")
    public List<Buy> account(int personId);
}
