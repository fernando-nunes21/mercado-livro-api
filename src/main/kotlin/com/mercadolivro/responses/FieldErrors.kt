package com.mercadolivro.responses

data class FieldErrors(
    var field: String,
    var message: String
) {
}