package com.qmtec.servicecore.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        //判断是否已有该用户登录的session
        if (session.getAttribute("userId") != null) {
            return true;
        } else {
            //跳转到登录页
            String url = "/login";
            String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + url;
            //response.sendRedirect(basePath);

            String XRequested = request.getHeader("X-Requested-With");
            if ("XMLHttpRequest".equals(XRequested)) {
                response.getWriter().write("notLoggedOn");
            } else {
                response.sendRedirect(basePath);
            }

        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info(request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(request.getRequestURI());
    }
}
