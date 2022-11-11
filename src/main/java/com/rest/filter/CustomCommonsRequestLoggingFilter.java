package com.rest.filter;

import org.springframework.core.annotation.Order;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author u2cc
 */
@Component
@Order(4)
public class CustomCommonsRequestLoggingFilter extends CommonsRequestLoggingFilter {
//Need to define in application.properties: logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=INFO
    public CustomCommonsRequestLoggingFilter(){

        super.setIncludeQueryString(true);
        super.setIncludePayload(true);
        super.setMaxPayloadLength(10000);
        super.setIncludeHeaders(false);
       // super.setAfterMessagePrefix("REQUEST DATA : ");
        super.setBeforeMessagePrefix("REQUEST DATA : ");
    }

    @Override
    protected boolean shouldLog(@NonNull HttpServletRequest request) {
        return logger.isInfoEnabled();
    }

    @Override
    protected void beforeRequest(@NonNull HttpServletRequest request, @NonNull String message) {
        logger.info(message);
    }

    /**
     * Writes a log message after the request is processed.
     */
    @Override
    protected void afterRequest(@NonNull HttpServletRequest request, @NonNull String message) {
       // logger.info(request.getContentType() + " - " + message);
    }
}
