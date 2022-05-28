package com.mercadolivro.events

import com.mercadolivro.model.Book
import com.mercadolivro.service.BookService
import com.mercadolivro.model.Purchase
import com.mercadolivro.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.*

@Component
class PurchaseEventListener(
    val bookService: BookService,
    val purchaseService: PurchaseService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        generateNfeToPurchase(purchaseEvent.purchase)
        updatePurchaseBooks(purchaseEvent.purchase.books)
    }

    private fun generateNfeToPurchase(purchase: Purchase) {
        val nfe = UUID.randomUUID().toString()
        purchase.nfe = nfe
        purchaseService.updatePurchase(purchase)
    }

    private fun updatePurchaseBooks(books: List<Book>){
        bookService.updatePurchaseBook(books)
    }

}