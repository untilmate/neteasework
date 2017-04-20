package cn.com.netease.models;

/**
 * 
 */
public class Trx {
    private Integer id;
    //内容ID
    private Integer contentId;
    //用户ID
    private Integer personId;
    //购买价格
    private Double price;
    //购买数量
    private Integer num;
    //购买时间
    private Long time;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getContentId() {
        return contentId;
    }
    public void setContentId(int contentId) {
        this.contentId = contentId;
    }
    public int getPersonId() {
        return personId;
    }
    public void setPersonId(int personId) {
        this.personId = personId;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public Long getTime() {
        return time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }

}
