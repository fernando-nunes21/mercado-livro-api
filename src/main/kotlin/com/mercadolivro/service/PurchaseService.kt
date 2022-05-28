package com.mercadolivro.service

import com.mercadolivro.model.Book
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.exceptions.ElementStatusException
import com.mercadolivro.model.Purchase
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val applicationEventPublisher: ApplicationEventPublisher
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