package com.mercadolivro.purcharse.events

import com.mercadolivro.purcharse.Purchase
import org.springframework.context.ApplicationEvent

class PurchaseEvent(
    source: Any,
    val purchase: Purchase

) : ApplicationEvent(source) {
}