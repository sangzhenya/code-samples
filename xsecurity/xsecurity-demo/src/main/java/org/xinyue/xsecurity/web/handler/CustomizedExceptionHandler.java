package org.xinyue.xsecurity.web.handler;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.xinyue.xsecurity.exception.CustomizedException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomizedExceptionHandler {
    private Log log = LogFactory.getLog(CustomizedExceptionHandler.class);

    @ExceptionHandler(CustomizedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handCustomizedException(CustomizedException ex) {
        Map<String, Object> result = new HashMap<>();
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        log.error(result);
        return result;

    }
}
