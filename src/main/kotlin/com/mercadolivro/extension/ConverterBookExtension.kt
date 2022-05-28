package com.mercadolivro.books.extension

import com.mercadolivro.model.Book
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Customer
import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest


fun PostBookRequest.toBook(customer : Customer) : Book {
    return Book(name = name, price = price, status = BookStatus.ACTIVE, customer = customer)
}

fun PutBookRequest.toBook(customer : Customer) : Book {
    return Book(name = name, price = price, status = BookStatus.valueOf(status), customer = customer)
}

fun Book.toResponse() : BookResponse {
    return BookResponse(id = id, name = name, price = price, status = status, customer = customer)
}