package com.mercadolivro.generic.responses

data class FieldErrors(
    var field: String,
    var message: String
) {
}