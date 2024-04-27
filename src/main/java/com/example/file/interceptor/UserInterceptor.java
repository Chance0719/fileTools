package com.example.file.interceptor;

import com.example.file.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截了非登录请求！===========" + request.getRequestURL().toString());
        try {
            HttpSession session = request.getSession();
            //统一拦截（查询当前session是否存在user）(这里user会在每次登录成功后，写入session)
            User user = (User) session.getAttribute("User");
            if (user != null) {
                return true;
            }
            //重定向登录页面
            response.sendRedirect("/login");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
