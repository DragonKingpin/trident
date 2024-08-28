package com.sauron.saurye.Interceptor;

import com.sauron.saurye.context.UserContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor implements HandlerInterceptor  {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        UserContext.setCurrentId(Long.valueOf(request.getHeader("user_id")));

        return true;
    }
}
