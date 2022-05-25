package com.mercadolivro.purcharse.controller.request

data class PostPurchaseRequest(
    var customerId : Int,
    var booksIds : List<Int>
) {
}