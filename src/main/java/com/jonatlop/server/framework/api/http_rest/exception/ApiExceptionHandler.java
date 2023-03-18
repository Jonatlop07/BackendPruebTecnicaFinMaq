package com.jonatlop.server.framework.api.http_rest.exception;

import com.jonatlop.server.core.abstraction.exception.CoreException;
import com.jonatlop.server.framework.api.http_rest.entity.common.ApiResponse;
import com.jonatlop.server.framework.api.http_rest.entity.common.CompoundResponse;
import com.jonatlop.server.framework.api.http_rest.mapper.HttpStatusMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CoreException.class})
    ResponseEntity<CompoundResponse> handleEmailAlreadyUsedException(CoreException ex) {
        HttpStatus httpStatus = HttpStatusMapper.from(ex.getCode());
        return ResponseEntity
            .status(httpStatus)
            .body(new CompoundResponse(new ApiResponse(false, ex.getMessage(), httpStatus, httpStatus.value())));
    }
}
