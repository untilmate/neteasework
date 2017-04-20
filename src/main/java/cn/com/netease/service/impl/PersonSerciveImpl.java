package cn.com.netease.service.impl;

import cn.com.netease.dao.PersonDao;
import cn.com.netease.models.Person;
import cn.com.netease.service.PersonService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PersonSerciveImpl implements PersonService {

    @Resource
    private PersonDao personDao;

    public String userlogin(Person person) {
        return personDao.login(person);
    }

    public int findtype(Person person) {
        return personDao.type(person);
    }

    public int id(String name) {
        return personDao.id(name);
    }
}
