package com.poly.lab8.component;

import com.poly.lab8.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Date;

@Component
public class LogInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        Account user = (Account) request.getSession().getAttribute("user");
        String name = (user != null) ? user.getUsername() : "Guest";
        System.out.println(request.getRequestURI()
                + ", " + new Date() + ", " + name);
        return true;
    }
}

