package com.mercadolivro.purcharse.service

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

    fun purchase(purchase: Purchase) {
        purchaseRepository.save(purchase)
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchase))
    }

    fun updatePurchase(purchase: Purchase) {
        purchaseRepository.save(purchase)
    }
}