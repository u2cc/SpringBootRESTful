package com.rest.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author u2cc
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    //his is used to perform operations before sending the request to the controller. This method should return true to return the response to the client.
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler){
        logger.info("preHandle - before controller processing request");
        return true;
    }

    @Override
    //This is used to perform operations before sending the response to the client.
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView){
        logger.info("postHandle - after controller processing, before sending out response");
    }

    @Override
    //This is used to perform operations after completing the request and response.
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex){
        logger.info("afterCompletion - after controller handing out response");
    }

}
