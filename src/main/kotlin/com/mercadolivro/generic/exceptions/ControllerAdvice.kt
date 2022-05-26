package com.mercadolivro.generic.exceptions

import com.mercadolivro.generic.enums.ErrorCode
import com.mercadolivro.generic.responses.ErrorResponse
import com.mercadolivro.generic.responses.InputFieldErrors
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.sql.SQLIntegrityConstraintViolationException

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(ElementNotFoundException::class)
    fun handlerElementNotFoundException(ex: ElementNotFoundException, request: WebRequest)
    : ResponseEntity<ErrorResponse> {
        var errorResponse = ErrorResponse(
            statusCode = HttpStatus.NOT_FOUND.value(),
            message = ex.message,
            errorCode = ex.errorCode,
            errors = null
        )

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun handlerMissingServletRequestParameterException(ex: MissingServletRequestParameterException, request: WebRequest)
    : ResponseEntity<ErrorResponse> {
        var errorResponse = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            message = ex.message,
            errorCode = ErrorCode.M0000.errorCode,
            errors = null
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException::class)
    fun handlerSQLIntegrityConstraintViolationException(
        ex: SQLIntegrityConstraintViolationException,
        request: WebRequest
    ) : ResponseEntity<ErrorResponse> {
        var errorResponse = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            message = "Duplicate entry for e-mail. Please try use another e-mail",
            errorCode = ErrorCode.M0000.errorCode,
            errors = null
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(ex: MethodArgumentNotValidException, request: WebRequest)
    : ResponseEntity<ErrorResponse> {
        var errorResponse = ErrorResponse(
            statusCode = HttpStatus.UNPROCESSABLE_ENTITY.value(),
            message = "Validation failed because are some error in field(s)",
            errorCode = ErrorCode.M0000.errorCode,
            errors = ex.bindingResult.fieldErrors.map {
                InputFieldErrors(field = it.field, error = it.defaultMessage.toString())
            }
        )

        return ResponseEntity(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY)
    }

    @ExceptionHandler(ElementStatusException::class)
    fun handlerElementStatusException(ex: ElementStatusException, request: WebRequest) : ResponseEntity<ErrorResponse> {
        var errorResponse = ErrorResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            message = ex.message,
            errorCode = ErrorCode.M0000.errorCode,
            errors = null
        )

        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }
}