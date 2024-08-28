package com.sauron.saurye.system.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.sauron.saurye.system.PredatorGenieBottle;

@Aspect
@Component
public class GenieAspect {
    @Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping) || " +
            "@annotation(org.springframework.web.bind.annotation.PatchMapping)" )
    public void beforeMapping( JoinPoint joinPoint ) {
//        Object[] args = joinPoint.getArgs();
//        HttpServletRequest request = null;
//        HttpServletResponse response = null;
//
//        for ( Object arg : args ) {
//            if (arg instanceof HttpServletRequest) {
//                request = (HttpServletRequest) arg;
//            }
//            if (arg instanceof HttpServletResponse) {
//                response = (HttpServletResponse) arg;
//            }
//        }
//
//        if (request != null && response != null) {
//            PredatorGenieBottle that = (PredatorGenieBottle)joinPoint.getThis();
//            that.afterGetArrived( request, response);
//        }
    }
}
