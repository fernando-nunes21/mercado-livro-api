package com.mercadolivro.purcharse.events

import com.mercadolivro.books.Book
import com.mercadolivro.books.service.BookService
import com.mercadolivro.purcharse.Purchase
import com.mercadolivro.purcharse.service.PurchaseService
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