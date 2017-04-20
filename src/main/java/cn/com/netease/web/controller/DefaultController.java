package cn.com.netease.web.controller;

import cn.com.netease.models.*;
import cn.com.netease.service.ContentService;
import cn.com.netease.service.PersonService;
import cn.com.netease.service.ProductService;
import cn.com.netease.service.TrxServices;
import cn.com.netease.utils.SesssionOP;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.List;

/**
 * 
 * 默认Controller
 */
@Controller
public class DefaultController {

    @Resource
    private SesssionOP sesssionOP;
    @Resource
    private ContentService contentService;
    @Resource
    private PersonService personService;
    @Resource
    private ProductService productService;
    @Resource
    private TrxServices trxServices;

    //提取Session
    public User getUser(HttpServletRequest request, ModelMap map){
        User user = sesssionOP.getSession(request);
        map.addAttribute("user",user);
        return user;
    }

    //展示页
    @RequestMapping("/")
    public String home(HttpServletRequest request,
                       ModelMap map){
        Integer personId = 0 ;
        User user = getUser(request,map);
        if(user != null){
            String name = user.getUsername();
            personId = personService.id(name);
        }
        List<Product> productList = productService.productList();
        map.addAttribute("productList",productList);
        return "index";
    }

    //查看页
    @RequestMapping("/show")
    public String show(@RequestParam("id") int id,
                       HttpServletRequest request,
                       ModelMap map){
        //根据id去数据库查询商品信息
        Trx trx = new Trx();
        trx.setContentId(id);
        User user = getUser(request,map);
        if(user != null){
            String name = user.getUsername();
            trx.setPersonId(personService.id(name));
        }else{
            trx.setPersonId(0);
        }
        Product product = trxServices.show(trx);
        map.addAttribute("product", product);
        return "show";
    }

    //登录页面
    @RequestMapping("/login")
    public String login(HttpServletRequest request,
                        ModelMap map) {
        getUser(request,map);
        return "login";
    }

    //退出页面
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        sesssionOP.destroySession(request);
        return "redirect:/login";
    }



    //登录验证
    @ResponseBody
    @RequestMapping("/api/login")
    public Object checklogin(@RequestParam("userName") String userName,
                               @RequestParam("password") String password,
                               HttpServletRequest request,
                               HttpServletResponse response,
                               ModelMap map){
        Person person = new Person();
        person.setUserName(userName);
        person.setPassword(password);
        //数据库中查询
        Integer type =null;
        String name = null;
        name = personService.userlogin(person);
        //如果返回值不为null，则是合法用户
        if(name != null){
            type = personService.findtype(person);
            //创建Session
            User user = new User();
            user.setUsername(userName);
            user.setUsertype(type);
            sesssionOP.setSession(user,request);
            //登录成功
            response.setStatus(200);
            map.addAttribute("code",200);
            map.addAttribute("message","Success");
            map.addAttribute("return",true);
            return map;
        }else{
            response.setStatus(400);
            map.addAttribute("code",400);
            map.addAttribute("message","用户名或者密码错误");
            map.addAttribute("return",false);
            return map;
        }
    }



    //发布页
    @RequestMapping("/public")
    public String publicPage(HttpServletRequest request,
                       ModelMap map){
        User user = getUser(request,map);
        if(user != null){
            return "public";
        }else{
            return "redirect:/login";
        }
    }

    //发布提交页
    @RequestMapping("/publicSubmit")
    public String publicSubmit(@RequestParam("title") String title,
                               @RequestParam("image") String image,
                               @RequestParam("detail") String detail,
                               @RequestParam("price") Double price,
                               @RequestParam("summary") String summary,
                               HttpServletRequest request,
                             ModelMap map){
        User user = getUser(request,map);
        //加入商品信息
        Content content = new Content();
        content.setTitle(title);
        content.setIcon(image);
        content.setText(detail);
        content.setPrice(price);
        content.setAbst(summary);
        //将商品信息写入数据库，作为事务执行
        contentService.addProduct(content);
        //从数据库查询刚刚写入的商品信息
        Integer id =null;
        id = contentService.chenkProduct(content);
        if(id != null){
            content.setId(id);
        }else{
            content = null;
        }
        map.addAttribute("product",content);
        return "publicSubmit";
    }

    //上传图片
    @ResponseBody
    @RequestMapping(value = "/api/upload")
    public Object upload(@RequestParam("file") MultipartFile file,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap map) {
        //获取文件保存路径
        String path = request.getSession().getServletContext().getRealPath("image");
        System.out.println(file.getContentType());
        //获取文件名称
        String fileName = file.getOriginalFilename();
        System.out.println(fileName);
        //保存文件
        File targetFile = new File(path, fileName);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        try {
            file.transferTo(targetFile);
            String im = "./image/"+fileName;
            response.setStatus(200);
            map.addAttribute("code",200);
            map.addAttribute("message","Success");
            map.addAttribute("result",im);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(400);
            map.addAttribute("code",400);
            map.addAttribute("message","保存失败");
            map.addAttribute("return",false);
            return map;
        }
    }

    //编辑页
    @RequestMapping("/edit")
    public String edit(@RequestParam("id") int id,
                       HttpServletRequest request,
                       ModelMap map){
        User user = getUser(request,map);
        if(user != null){
            Product product = productService.showProduct(id);
            map.addAttribute("product", product);
            return "edit";
        }else{
            return "redirect:/login";
        }
    }


    //编辑提交页
    @RequestMapping("/editSubmit")
    public String editSubmit(@RequestParam("id") int id,
                             @RequestParam("title") String title,
                             @RequestParam("image") String image,
                             @RequestParam("detail") String detail,
                             @RequestParam("price") Double price,
                             @RequestParam("summary") String summary,
                             HttpServletRequest request,
                             ModelMap map) {
        //加入商品信息
        Content content = new Content();
        content.setId(id);
        content.setTitle(title);
        content.setIcon(image);
        content.setText(detail);
        content.setPrice(price);
        content.setAbst(summary);
        System.out.println(title + image  + detail + price + summary);
        //将商品信息写入数据库，作为事务执行
        contentService.editProduct(content);
        //从数据库查询刚刚写入的商品信息
        Integer id1 =null;
        id1 = contentService.chenkProduct(content);
        if(id1 != null){
            content.setId(id1);
        }else{
            content = null;
        }
        map.addAttribute("product",content);
        return "publicSubmit";
    }

    //账务
    @RequestMapping("/account")
    public String account(HttpServletRequest request,
                          ModelMap map){
        User user = getUser(request,map);
        if(user != null){
            int personId = 1;
            List<Buy> buyList = trxServices.account(personId);
            //数据库中查询交易记录
            map.addAttribute("buyList", buyList);
            return "account";
        }else{
            return "redirect:/login";
        }
    }

    //购物车
    @RequestMapping("/settleAccount")
    public String settlerAccount(HttpServletRequest request,
                                 ModelMap map){
        User user = getUser(request,map);
        if(user != null){
            return "settleAccount";
        }else{
            return "redirect:/login";
        }
    }

    //购买
    @ResponseBody
    @RequestMapping("/api/buy")
    public Object buy(@RequestBody List<Buy> buyList,
                      HttpServletRequest request,
                      HttpServletResponse response,
                      ModelMap map){
        Trx trx = new Trx();
        Date date = new Date();
        Long time = date.getTime();
        User user = getUser(request,map);
        if(user != null){
            String name = user.getUsername();
            trx.setPersonId(personService.id(name));
        }else{
            trx.setPersonId(0);
        }
        int i = 0;
        for(Buy buy:buyList){
            trx.setContentId(buy.getId());
            trx.setNum(buy.getNumber());
            trx.setTime(time);
            trx.setPrice(contentService.productPrice(buy.getId()));
            int a = trxServices.buyProduct(trx);
            i =+ a;
        }
        if(i != 0){
            response.setStatus(200);
            map.addAttribute("code",200);
            map.addAttribute("message","Success");
            map.addAttribute("return",true);
            return map;
        }else {
            response.setStatus(400);
            map.addAttribute("code",400);
            map.addAttribute("message","购买失败");
            map.addAttribute("return",false);
            return map;
        }

    }

    //删除
    @ResponseBody
    @RequestMapping("/api/delete")
    public Object delete(@RequestParam("id") int id,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         ModelMap map){
        Boolean del = contentService.delProduct(id);
        if(del){
            response.setStatus(200);
            map.addAttribute("code",200);
            map.addAttribute("message","Success");
            map.addAttribute("return",true);
            return map;
        }else {
            response.setStatus(400);
            map.addAttribute("code",400);
            map.addAttribute("message","删除失败");
            map.addAttribute("return",false);
            return map;
        }

    }
}
