package com.mercadolivro.generic.responses

import org.springframework.validation.FieldError

data class ErrorResponse(
    var statusCode: Int,
    var message: String?,
    var errorCode: String,
    var errors: List<InputFieldErrors>?
) {
}