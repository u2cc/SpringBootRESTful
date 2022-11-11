package com.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author u2cc
 * An OncePerRequestFilter implementation to log response content.
 */
@Component
@Order(3)
public class ResponseLoggingFilter extends OncePerRequestFilter {

    private final static Logger LOG = LoggerFactory.getLogger(ResponseLoggingFilter.class);


    @Override
    //https://www.baeldung.com/spring-null-safety-annotations
    //because in the package-info.java file at org.springframework.web.filter we defined @nonnullapi and @nonnullfields package-wide therefore we need to enforce the same
    //at sub-class level
    public void doFilterInternal( @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        chain.doFilter(requestWrapper, responseWrapper);

       // String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), response.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        String requestBody = "";
      //  String responseBody = "";

        LOG.info(
               "RESPONSE DATA : {} - {}", responseWrapper.getContentType(), responseBody
                );
        responseWrapper.copyBodyToResponse();


    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding){
        try {
            return new String(contentAsByteArray, 0,    Math.min(contentAsByteArray.length,1000), characterEncoding);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error occurred at getStringValue when converting byte to String. {}", e.getMessage());
            LOG.error("Exception: ", e);
        }
        return "";
    }
}
