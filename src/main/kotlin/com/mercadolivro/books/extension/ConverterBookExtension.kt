package com.mercadolivro.books.extension

import com.mercadolivro.books.Book
import com.mercadolivro.books.enums.BookStatus
import com.mercadolivro.customers.Customer
import com.mercadolivro.customers.controller.request.PostBookRequest
import com.mercadolivro.customers.controller.request.PutBookRequest


fun PostBookRequest.toBook(customer : Customer) : Book {
    return Book(name = name, price = price, status = BookStatus.ACTIVE, customer = customer)
}

fun PutBookRequest.toBook(customer : Customer) : Book {
    return Book(name = name, price = price, status = BookStatus.valueOf(status), customer = customer)
}