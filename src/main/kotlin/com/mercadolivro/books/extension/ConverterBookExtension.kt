package com.mercadolivro.books.extension

import com.mercadolivro.books.Book
import com.mercadolivro.books.controller.response.BookResponse
import com.mercadolivro.books.enums.BookStatus
import com.mercadolivro.customers.Customer
import com.mercadolivro.books.controller.request.PostBookRequest
import com.mercadolivro.books.controller.request.PutBookRequest


fun PostBookRequest.toBook(customer : Customer) : Book {
    return Book(name = name, price = price, status = BookStatus.ACTIVE, customer = customer)
}

fun PutBookRequest.toBook(customer : Customer) : Book {
    return Book(name = name, price = price, status = BookStatus.valueOf(status), customer = customer)
}

fun Book.toResponse() : BookResponse {
    return BookResponse(id = id, name = name, price = price, status = status, customer = customer)
}