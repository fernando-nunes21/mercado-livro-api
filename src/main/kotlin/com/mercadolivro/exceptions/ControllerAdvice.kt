package com.mercadolivro.exceptions

import com.mercadolivro.responses.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(ElementNotFoundException::class)
    fun handlerElementNotFoundException(
        ex: ElementNotFoundException, request: WebRequest
    ) : ResponseEntity<ErrorResponse> {
        var errorResponse = ErrorResponse(
            statusCode = HttpStatus.NOT_FOUND.value(),
            message = ex.message,
            errorCode = ex.errorCode,
            errors = null
        )

        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }
}