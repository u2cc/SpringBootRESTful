package com.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author u2cc
 * A generic Filter implementation to mark the start and finish of a request handling at Filter stage and provide
 * Mapped Diagnostic Context (MDC) id.
 */

@Component
@Order(2)
//Since Spring 4.0, it supports the ordering of injected components to a collection. As a result, Spring will inject the auto-wired beans of the same type based on their order value.
public class TransactionMDCFilter implements Filter {

    private final static Logger LOG = LoggerFactory.getLogger(TransactionMDCFilter.class);

    @Override
    /*
    While a superclass method throws an exception while overriding it you need to follow the certain rules.

    The sub class method Should throw Same exception or, sub type −
    It should not throw an exception of super type −
    You may leave the method in sub class Without throwing any exception
     */
    public void init(final FilterConfig filterConfig)  {
        LOG.info("Initializing filter :{}", this);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        try{
            MDC.put("userId", UUID.randomUUID().toString());
            LOG.info("Starting Transaction for req :{} - {}", req.getMethod(), req.getRequestURI());
            chain.doFilter(request, response);
            LOG.info("Committing Transaction for req :{} - {}", req.getMethod(), req.getRequestURI());
        }finally{
            MDC.clear();
        }
    }
}
