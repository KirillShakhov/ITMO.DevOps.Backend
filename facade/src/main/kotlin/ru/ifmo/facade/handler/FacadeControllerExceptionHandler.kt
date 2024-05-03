package ru.ifmo.facade.handler

import feign.FeignException
import lombok.NonNull;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.servlet.ServletException;


@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class FacadeControllerExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(ServletException::class)
    fun handleAccessToken(
        @NonNull exception: RuntimeException,
        @NonNull request: WebRequest?
    ): ResponseEntity<Any> {
        return handleExceptionInternal(
            exception, exception.message,
            HttpHeaders(), HttpStatus.BAD_REQUEST, request!!
        )
    }

    @ExceptionHandler(FeignException.BadRequest::class)
    fun handleBadRequest(
        @NonNull exception: RuntimeException,
        @NonNull request: WebRequest?
    ): ResponseEntity<Any> {
        return handleExceptionInternal(
            exception, exception.message,
            HttpHeaders(), HttpStatus.BAD_REQUEST, request!!
        )
    }

    @ExceptionHandler(FeignException.Forbidden::class)
    fun handleForbidden(
        @NonNull exception: RuntimeException,
        @NonNull request: WebRequest?
    ): ResponseEntity<Any> {
        return handleExceptionInternal(
            exception, exception.message,
            HttpHeaders(), HttpStatus.FORBIDDEN, request!!
        )
    }
}