package com.mercadolivro.books.controller

import com.mercadolivro.books.Book
import com.mercadolivro.books.extension.toBook
import com.mercadolivro.books.service.BookService
import com.mercadolivro.customers.controller.request.PostBookRequest
import com.mercadolivro.customers.controller.request.PutBookRequest
import com.mercadolivro.customers.service.CustomerService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/books")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {

    @GetMapping
    fun getAllBooks(
        @RequestParam name : String?,
        @RequestParam(required = true) limit : Int,
        @RequestParam(required = true) offset : Int
    ) : ResponseEntity<List<Book>> {
        return ResponseEntity(bookService.getAllBooks(name, limit, offset), HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id : Int) : ResponseEntity<Book> {
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping
    fun createBook(@RequestBody request: PostBookRequest) : ResponseEntity<Any> {
        val customer = customerService.getCustomerById(request.customerId)
        return ResponseEntity(bookService.createBook(request.toBook(customer)),HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun editBook(@PathVariable id: Int, @RequestBody request: PutBookRequest) : ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Int) : ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}