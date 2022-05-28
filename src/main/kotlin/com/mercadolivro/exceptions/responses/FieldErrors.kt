package com.mercadolivro.exceptions.responses

data class FieldErrors(
    var field: String,
    var message: String
) {
}