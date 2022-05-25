package com.mercadolivro.purcharse.mapper

import com.mercadolivro.books.service.BookService
import com.mercadolivro.customers.service.CustomerService
import com.mercadolivro.purcharse.Purchase
import com.mercadolivro.purcharse.controller.request.PostPurchaseRequest
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    val customerService: CustomerService,
    val bookService: BookService
){
    fun toModel(request : PostPurchaseRequest) : Purchase {
        val customer = customerService.getCustomerById(request.customerId)
        val books = bookService.getAllBooksByIds(request.booksIds)
        return Purchase(
            customer = customer,
            books = books,
            priceTotal = books.sumOf { it.price!! }
        )
    }
}