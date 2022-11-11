package com.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.lang.NonNullApi;
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
 */
@Component
@Order(2)
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private final static Logger LOG = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);


    @Override
    //https://www.baeldung.com/spring-null-safety-annotations
    //because in the package-info.java file at org.springframework.web.filter we defined @nonnullapi and @nonnullfields package-wide therefore we need to enforce the same
    //at sub-class level
    public void doFilterInternal( @NonNull HttpServletRequest request, @NonNull HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(request);
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(response);

        chain.doFilter(requestWrapper, responseWrapper);
        LOG.info(
                "Logging Response :{}",
                responseWrapper.getContentType() );

        String requestBody = getStringValue(requestWrapper.getContentAsByteArray(), response.getCharacterEncoding());
        String responseBody = getStringValue(responseWrapper.getContentAsByteArray(), response.getCharacterEncoding());

        LOG.info(
                "FINISHED PROCESSING : METHOD={}; REQUESTURI={}; REQUEST PAYLOAD={}; RESPONSE CODE={}; RESPONSE={}",
                request.getMethod(), request.getRequestURI(), requestBody, response.getStatus(), responseBody
                );
        responseWrapper.copyBodyToResponse();


    }

    private String getStringValue(byte[] contentAsByteArray, String characterEncoding){
        try {
            return new String(contentAsByteArray, 0,    contentAsByteArray.length, characterEncoding);
        } catch (UnsupportedEncodingException e) {
            LOG.error("Error occurred at getStringValue when converting byte to String. {}", e.getMessage());
            LOG.error("Exception: ", e);
        }
        return "";
    }
}
