package com.mercadolivro.purcharse.controller.response

import com.mercadolivro.books.Book
import java.math.BigDecimal
import java.time.LocalDateTime

data class PurchaseResponse(
    val id: Int,
    val customerName: String,
    val books : List<Book>,
    val nfe: String,
    val priceTotal: BigDecimal,
    val soldAt: LocalDateTime
) {
}