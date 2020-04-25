package com.leeyaonan.controller;

import com.leeyaonan.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * @Author leeyaonan
 * @Date 2020/4/19 23:56
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    /**
     * 需求，访问url，返回当前系统时间
     * url: http://localhost:8080/demo/handle01
     * @return
     */
    @RequestMapping("/handle01")
    public ModelAndView handle01() {
        Date date = new Date();
        // 封装了数据和页面信息的 ModelAndView
        ModelAndView modelAndView = new ModelAndView();
        // addObject 其实是向请求域中request.setAttribute("date", date)
        modelAndView.addObject("date", date);
        // 视图信息（封装跳转的页面信息）
        modelAndView.setViewName("success");    // 逻辑视图名
        return modelAndView;
    }

    /**
     * http://localhost:8080/demo/handle02
     * @param modelMap
     * @return
     */
    @RequestMapping("handle02")
    public String handle02(ModelMap modelMap) {
        Date date = new Date();
        modelMap.addAttribute("date", date);
        System.out.println("modelmap" + modelMap);
        return "success";
    }

    /**
     * http://localhost:8080/demo/handle03
     * @param model
     * @return
     */
    @RequestMapping("handle03")
    public String handle03(Model model) {
        Date date = new Date();
        model.addAttribute("date", date);
        System.out.println("model" + model);
        return "success";
    }
    /**
     * http://localhost:8080/demo/handle04
     * @param map
     * @return
     */
    @RequestMapping("handle04")
    public String handle04(Map<String, Object> map) {
        Date date = new Date();
        map.put("date", date);
        System.out.println("map" + map);
        return "success";
    }

    /**
     * SpringMVC在handler方法上传入Map，ModelMap，Model参数，
     * 并向这些参数中保存数据（放入请求域）都可以在页面获取到
     * 他们之间有什么关系？？？
     * --> 打印出他们的类型
         * modelmap{date=Tue Apr 21 13:49:11 CST 2020}
         * model{date=Tue Apr 21 13:49:29 CST 2020}
         * map{date=Tue Apr 21 13:49:35 CST 2020}
     * 发现他们底层运行时的具体类型都是BindingAwareModelMap，
     * 相当于给它保存的数据都会放在请求域中
     * Map（jdk中的接口）
     * Model（spring中的接口）
     * ModelMap（class，实现map接口）
     * BindingAwareModelMap继承了ExtendedModelMap，
     * ExtendedModelMap继承了ModelMap，实现了Model接口
     */

    /**
     * 绑定日期类型参数
     * 定义一个SpringMVC的类型转换器  接口，扩展实现接口接口，注册你的实现
     * @param birthday
     * @return
     */
    @RequestMapping("/handle06")
    public ModelAndView handle06(Date birthday) {
        Date date = new Date();ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }



    /*
     * restful  get   /demo/handle/15
     */
    @RequestMapping(value = "/handle/{id}",method = {RequestMethod.GET})
    public ModelAndView handleGet(@PathVariable("id") Integer id) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * restful  post  /demo/handle
     */
    @RequestMapping(value = "/handle",method = {RequestMethod.POST})
    public ModelAndView handlePost(String username) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * restful  put  /demo/handle/15/lisi
     */
    @RequestMapping(value = "/handle/{id}/{name}",method = {RequestMethod.PUT})
    public ModelAndView handlePut(@PathVariable("id") Integer id,@PathVariable("name") String username) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /*
     * restful  delete  /demo/handle/15
     */
    @RequestMapping(value = "/handle/{id}",method = {RequestMethod.DELETE})
    public ModelAndView handleDelete(@PathVariable("id") Integer id) {

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }



    @RequestMapping("/handle07")
    // 添加@ResponseBody之后，不再走视图解析器那个流程，而是等同于response直接输出数据

    public @ResponseBody
    User handle07(@RequestBody User user) {

        // 业务逻辑处理，修改name为张三丰
        user.setName("张三丰");
        return user;
    }


    /**
     * 文件上传
     * @return
     */
    @RequestMapping(value = "/upload")
    public ModelAndView upload(MultipartFile uploadFile, HttpSession session) throws IOException {

        // 处理上传文件
        // 重命名，原名123.jpg ，获取后缀
        String originalFilename = uploadFile.getOriginalFilename();// 原始名称
        // 扩展名  jpg
        String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1, originalFilename.length());
        String newName = UUID.randomUUID().toString() + "." + ext;

        // 存储,要存储到指定的文件夹，/uploads/yyyy-MM-dd，考虑文件过多的情况按照日期，生成一个子文件夹
        String realPath = session.getServletContext().getRealPath("/uploads");
        String datePath = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File folder = new File(realPath + "/" + datePath);

        if(!folder.exists()) {
            folder.mkdirs();
        }


        // 存储文件到目录
        uploadFile.transferTo(new File(folder,newName));


        // TODO 文件磁盘路径要更新到数据库字段

        Date date = new Date();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("date",date);
        modelAndView.setViewName("success");
        return modelAndView;
    }


    /**
     * SpringMVC 重定向时参数传递的问题
     * 转发：A 找 B 借钱400，B没有钱但是悄悄的找到C借了400块钱给A
     *      url不会变,参数也不会丢失,一个请求
     * 重定向：A 找 B 借钱400，B 说我没有钱，你找别人借去，那么A 又带着400块的借钱需求找到C
     *      url会变,参数会丢失需要重新携带参数,两个请求
     */

    @RequestMapping("/handleRedirect")
    public String handleRedirect(String name, RedirectAttributes redirectAttributes) {

        //return "redirect:handle01?name=" + name;  // 拼接参数安全性、参数长度都有局限
        // addFlashAttribute方法设置了一个flash类型属性，该属性会被暂存到session中，在跳转到页面之后该属性销毁
        redirectAttributes.addFlashAttribute("name",name);
        return "redirect:handle01";

    }
}
