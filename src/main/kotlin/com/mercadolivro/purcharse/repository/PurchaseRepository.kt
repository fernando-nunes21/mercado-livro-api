package com.mercadolivro.purcharse.repository

import com.mercadolivro.purcharse.Purchase
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<Purchase, Int> {
    @Query(value="SELECT * FROM Purchases WHERE customer_id = ?1 ORDER BY id limit ?2 offset ?3", nativeQuery = true)
    fun getAllCustomerPurchases(customerId: Int, limit: Int, offset: Int) : List<Purchase>

}