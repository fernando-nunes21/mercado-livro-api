package com.mercadolivro.books.controller.response

import com.mercadolivro.books.enums.BookStatus
import com.mercadolivro.customers.Customer
import java.math.BigDecimal

data class BookResponse(

    var id: Int? = null,
    var name: String?,
    var price: BigDecimal?,
    var status: BookStatus? = null,
    var customer: Customer? = null

) {
}
