package com.mercadolivro.enums

enum class ErrorCode(var message: String, var errorCode: String) {

    M0000(message = "Invalid Request", errorCode = "M0000"),

    //Customer errors
    M0001(message = "Not Found Customer with this id", errorCode = "M0001"),
    M0002(message = "Can't create customer. Invalid Fields", errorCode = "M0002"),
    M0003(message = "Can't edit customer. Invalid Fields", errorCode = "M0003"),

    //Book errors
    M0101(message = "Not Found Book with this id", errorCode = "M0101"),
    M0102(message = "Can't create book. Invalid Fields", errorCode = "M0102"),
    M0103(message = "Can't edit book. Invalid Fields", errorCode = "M0103")

}