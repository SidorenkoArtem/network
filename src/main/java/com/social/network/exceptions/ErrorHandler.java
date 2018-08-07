package com.social.network.exceptions;

import com.social.network.model.dto.ValidationErrorFieldDto;
import com.social.network.model.responces.ApplicationErrorResponse;
import com.social.network.model.responces.ValidationErrorsResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ErrorHandler {


    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ApplicationErrorResponse handleApplicationException(final Exception e) {
        return handleException(e);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorsResponse processValidationError(final MethodArgumentNotValidException ex) {
        final BindingResult result = ex.getBindingResult();
        final List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }


    @ExceptionHandler({NeedTokenException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApplicationErrorResponse processNeedTokenException(final ApplicationException ex) {
        return handleApplicationException(ex);
    }

    @ExceptionHandler({InvalidAuthorizationHeaderException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ApplicationErrorResponse processInvalidTokenException(final ApplicationException ex) {
        return handleApplicationException(ex);
    }


    private ApplicationErrorResponse handleException(final Exception e) {
        final ErrorCode errorCode = getErrorCodeForException(e);
        //LOG.warn(e.getMessage(), e);
        return new ApplicationErrorResponse(errorCode.getErrorCode(), errorCode.getMessage());
    }

    private ValidationErrorsResponse processFieldErrors(final List<FieldError> fieldErrors) {
        return new ValidationErrorsResponse(fieldErrors.stream()
                .map(fieldError -> new ValidationErrorFieldDto(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList()));
    }


    private ErrorCode getErrorCodeForException(final Exception e) {
        if (e instanceof ApplicationException) {
            return ((ApplicationException) e).getErrorCode();
        } else {
            return ErrorCode.INTERNAL_SERVER;
        }
    }
}
