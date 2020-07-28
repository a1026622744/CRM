package com.yang.web.filter;

import com.yang.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入到验证有没有登录过的过滤器");
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        String path=request.getServletPath();

        if("/login.jsp".equals(path)||"/settings/user/login.do".equals(path)){
            //放行
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            //如果user不为空，说明登陆过
            if(user!=null){
                filterChain.doFilter(servletRequest,servletResponse);
            }else {
                //如果user为空
                //重定向到登录页
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }
        }



    }
}
