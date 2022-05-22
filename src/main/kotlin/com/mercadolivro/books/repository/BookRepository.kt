package com.mercadolivro.books.repository

import com.mercadolivro.books.Book
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {

    @Query(value="SELECT * FROM Customers WHERE name CONTAINS ?1 ORDER BY id limit ?2 offset ?3", nativeQuery = true)
    fun findByNameWithPagination(name: String, limit: Int, offset: Int) : List<Book>

    @Query(value="SELECT * FROM Books ORDER BY id limit ?1 offset ?2", nativeQuery = true)
    fun findAllBooksWithPagination(limit: Int, offset: Int) : List<Book>

}