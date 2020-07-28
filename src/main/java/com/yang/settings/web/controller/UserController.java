package com.yang.settings.web.controller;

import com.yang.settings.domain.User;
import com.yang.settings.service.Impl.UserServiceImpl;
import com.yang.settings.service.UserService;
import com.yang.utils.MD5Util;
import com.yang.utils.PrintJson;
import com.yang.utils.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入用户");

        String path=request.getServletPath();

        if("/settings/user/login.do".equals(path)){
            login(request,response);
        }else if ("/settings/user/xxx.do".equals(path)){

        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入验证登录操作");
        String loginAct=request.getParameter("loginAct");
        String loginPwd=request.getParameter("loginPwd");
        System.out.println(loginAct);
        System.out.println(loginPwd);
        //将密码的明文转换为md5
        loginPwd= MD5Util.getMD5(loginPwd);

        //接收浏览器端的ip地址
        String ip=request.getRemoteAddr();
        System.out.println("ip:"+ip);

        //业务层开发，统一使用代理类形态的接口对象
        UserService userService= (UserService) ServiceFactory.getService(new UserServiceImpl());

        try{

            User user=userService.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);

            //如果程序执行到此处，说明业务层没有为控制器抛出任何异常
            //表示登陆成功
            PrintJson.printJsonFlag(response,true);
        }catch (Exception e){
            e.printStackTrace();
            //一旦程序执行了catch块的信息，说明业务层为我们验证登录失败
            //表示登录失败
            String msg=e.getMessage();
            Map<String,Object> map= new HashMap<String, Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }
}
