package com.mercadolivro.customers.repository

import com.mercadolivro.customers.Customer
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Int> {

    @Query(value="SELECT * FROM Customers WHERE name CONTAINS ?1 ORDER BY id limit ?2 offset ?3", nativeQuery = true)
    fun findByNameWithPagination(name: String, limit: Int, offset: Int) : List<Customer>

    @Query(value="SELECT * FROM Customers ORDER BY id limit ?1 offset ?2", nativeQuery = true)
    fun findAllCustomersWithPagination(limit: Int, ofsset: Int) : List<Customer>

}