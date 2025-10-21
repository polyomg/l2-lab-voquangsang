package com.poly.lab5.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UtilityService {

    @Service
    public static class ParamService {
        @Autowired HttpServletRequest request;

        public String getString(String name, String def) {
            String v = request.getParameter(name);
            return (v == null) ? def : v;
        }

        public int getInt(String name, int def) {
            try { return Integer.parseInt(request.getParameter(name)); }
            catch (Exception e) { return def; }
        }

        public double getDouble(String name, double def) {
            try { return Double.parseDouble(request.getParameter(name)); }
            catch (Exception e) { return def; }
        }

        public boolean getBoolean(String name, boolean def) {
            String v = request.getParameter(name);
            return (v == null) ? def : Boolean.parseBoolean(v);
        }

        public Date getDate(String name, String pattern) {
            try { return new SimpleDateFormat(pattern).parse(request.getParameter(name)); }
            catch (Exception e) { return null; }
        }

        public File save(MultipartFile file, String path) {
            if (file == null || file.isEmpty()) return null;
            try {
                String real = request.getServletContext().getRealPath(path);
                File dir = new File(real);
                if (!dir.exists()) dir.mkdirs();
                File saved = new File(dir, System.currentTimeMillis() + "_" + file.getOriginalFilename());
                file.transferTo(saved);
                return saved;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Service
    public static class CookieService {
        @Autowired HttpServletRequest req;
        @Autowired HttpServletResponse res;

        public Cookie get(String name) {
            Cookie[] cookies = req.getCookies();
            if (cookies == null) return null;
            for (Cookie c : cookies) if (c.getName().equals(name)) return c;
            return null;
        }

        public String getValue(String name) {
            Cookie c = get(name);
            return (c == null) ? "" : c.getValue();
        }

        public void add(String name, String value, int hours) {
            Cookie c = new Cookie(name, value);
            c.setPath("/");
            c.setMaxAge(hours * 3600);
            res.addCookie(c);
        }

        public void remove(String name) {
            Cookie c = new Cookie(name, "");
            c.setMaxAge(0);
            c.setPath("/");
            res.addCookie(c);
        }
    }

    @Service
    public static class SessionService {
        @Autowired HttpSession session;
        public <T> T get(String name) { return (T) session.getAttribute(name); }
        public void set(String name, Object value) { session.setAttribute(name, value); }
        public void remove(String name) { session.removeAttribute(name); }
    }
}

