package com.guet.courseselecthelper.interceptor;


import com.guet.courseselecthelper.common.entity.Result;
import com.guet.courseselecthelper.common.util.JwtUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse resp, Object handler) throws Exception {
        String token = request.getHeader("token");
        // 判断令牌是否存在，如果不存在，返回错误结果（是否存在）
        if (!StringUtils.hasLength(token)) {
            Result error = Result.error("Not Logged In");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        // 解析token，解析失败返回错误结果（是否有效）
        try {
            JwtUtils.getUsernameFromToken(token);
        } catch (Exception e) { //解析失败
            e.printStackTrace();
            Result error = Result.error("Login Error");
            String notLogin = JSONObject.toJSONString(error);
            resp.getWriter().write(notLogin);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
