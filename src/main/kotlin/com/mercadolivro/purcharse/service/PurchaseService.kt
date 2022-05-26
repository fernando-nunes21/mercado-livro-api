package com.mercadolivro.purcharse.service

import com.mercadolivro.books.Book
import com.mercadolivro.books.enums.BookStatus
import com.mercadolivro.generic.exceptions.ElementStatusException
import com.mercadolivro.purcharse.Purchase
import com.mercadolivro.purcharse.events.PurchaseEvent
import com.mercadolivro.purcharse.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    val purchaseRepository: PurchaseRepository,
    val applicationEventPublisher: ApplicationEventPublisher
) {

    fun getAllCustomerPurchase(customerId: Int, limit: Int, offset:Int) : List<Purchase> {
        return purchaseRepository.getAllCustomerPurchases(customerId, limit, offset)
    }

    fun purchase(purchase: Purchase) {
        purchase.books.forEach{ isBookNotSold(it) }
        purchaseRepository.save(purchase)
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))
    }

    fun updatePurchase(purchase: Purchase) {
        purchaseRepository.save(purchase)
    }

    private fun isBookNotSold(book: Book) : Boolean {
        if(book.status == BookStatus.SOLD) {
            throw ElementStatusException("O livro do id ${book.id} está com status SOLD")
        } else {
            return true
        }
    }
}