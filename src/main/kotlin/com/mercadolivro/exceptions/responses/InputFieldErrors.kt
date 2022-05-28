package com.mercadolivro.exceptions.responses

data class InputFieldErrors(
    var field: String,
    var error: String
) {

}
