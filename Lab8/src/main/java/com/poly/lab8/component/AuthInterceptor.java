package com.poly.lab8.component;

import com.poly.lab8.entity.Account;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    HttpSession session;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        String uri = request.getRequestURI();
        session.setAttribute("securityUri", uri);
        Account user = (Account) session.getAttribute("user");

        // 🔸 Nếu chưa đăng nhập → chuyển về login
        if (user == null) {
            response.sendRedirect("/auth/login?error=unauthorized");
            return false;
        }

        // 🔸 Nếu user là admin mà truy cập trang user → ép về admin
        if (user.isAdmin() &&
                (uri.startsWith("/account") || uri.startsWith("/order"))) {
            response.sendRedirect("/admin/home/index");
            return false;
        }

        // 🔸 Nếu KHÔNG phải admin mà truy cập trang admin → chặn
        if (uri.startsWith("/admin") && !uri.equals("/admin/home/index") && !user.isAdmin()) {
            response.sendRedirect("/auth/login?error=unauthorized");
            return false;
        }

        return true;
    }
}
