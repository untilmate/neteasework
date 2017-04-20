package cn.com.netease.service;

import cn.com.netease.models.Person;

/**
 * 
 * Person表操作类
 */
public interface PersonService {
    //用户登录
    String userlogin(Person person);
    //查询type
    int findtype(Person person);
    //查询用户id
    int id(String name);
}
