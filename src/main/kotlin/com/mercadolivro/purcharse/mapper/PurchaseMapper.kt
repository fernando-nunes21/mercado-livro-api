package com.mercadolivro.purcharse.mapper

import com.mercadolivro.books.service.BookService
import com.mercadolivro.customers.service.CustomerService
import com.mercadolivro.purcharse.Purchase
import com.mercadolivro.purcharse.controller.request.PostPurchaseRequest
import com.mercadolivro.purcharse.controller.response.PurchaseResponse
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

    fun toListResponse(request: List<Purchase>) : List<PurchaseResponse> {
        var response : MutableList<PurchaseResponse> = ArrayList()
        request.forEach{
            val requestItem = PurchaseResponse(
                id = it.id!!,
                customerName = it.customer.name!!,
                books = it.books,
                nfe = it.nfe!!,
                priceTotal = it.priceTotal!!,
                soldAt = it.createdAt
            )
            response.add(requestItem)
        }
        return response.toList()
    }
}