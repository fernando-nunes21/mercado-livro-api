package com.mercadolivro.exceptions.responses

data class ErrorResponse(
    var statusCode: Int,
    var message: String?,
    var errorCode: String,
    var errors: List<InputFieldErrors>?
) {
}