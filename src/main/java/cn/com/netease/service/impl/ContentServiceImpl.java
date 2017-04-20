package cn.com.netease.service.impl;

import cn.com.netease.dao.ContentDao;
import cn.com.netease.models.Content;
import cn.com.netease.service.ContentService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class ContentServiceImpl implements ContentService {

    @Resource
    private ContentDao contentDao;

    @Transactional
    public void addProduct(Content content) {
        contentDao.addProduct(content);
    }

    public int chenkProduct(Content content) {
        return contentDao.checkProduct(content);
    }

    public Content findProduct(int id) {
        return contentDao.findProduct(id);
    }

    @Transactional
    public void editProduct(Content content) {
        contentDao.editProduct(content);
    }

    @Transactional
    public Boolean delProduct(int id) {
        int ret = contentDao.delProduct(id);
        return ret>0;
    }

    public Double productPrice(int id) {
        return contentDao.productPrice(id);
    }
}
