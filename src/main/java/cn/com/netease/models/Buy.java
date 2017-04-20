package cn.com.netease.models;

/**
 * 
 */
public class Buy {
    //产品ID content表
    private int id;
    //标题  content表
    private String title;
    //图片 content表
    private String image;
    //购买时的价格 trx表
    private double buyPrice;
    //购买数量  查询数量相加
    private int buyNum;
    private int number;
    //购买时间  trx表
    private Long buyTime;
    //购买的金额  价格相加

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }

    private Double total;

    /**
     * SQL
     * trx表  contentId  关联  content表  id
     * trx表  personId  关联  person表  id
     *
     * 需求：根据person的id，查询
     * content表：id，title,image
     * trx表：price，time
     *
     * select trx.price,trx.time,content.id,content.title,content.image
     * 	from trx
     * 		left join content.id = trx.contentId
     * 	where peronId = #{id};
     *
     * @return
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Long getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Long buyTime) {
        this.buyTime = buyTime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
