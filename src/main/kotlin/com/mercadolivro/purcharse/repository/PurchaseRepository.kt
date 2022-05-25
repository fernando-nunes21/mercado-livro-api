package com.mercadolivro.purcharse.repository

import com.mercadolivro.purcharse.Purchase
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<Purchase, Int> {

}