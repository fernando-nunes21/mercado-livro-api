package com.mercadolivro.controller

import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.books.extension.toBook
import com.mercadolivro.books.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.model.Customer
import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/v1/books")
class BookController(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    @GetMapping
    fun getAllBooks(
        @RequestParam name : String?,
        @RequestParam(required = true) limit : Int,
        @RequestParam(required = true) offset : Int
    ) : ResponseEntity<List<BookResponse>> {
        return ResponseEntity(bookService.getAllBooks(name, limit, offset).map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id : Int) : ResponseEntity<BookResponse> {
        return ResponseEntity(bookService.getBookById(id).toResponse(), HttpStatus.OK)
    }

    @GetMapping("/active")
    fun getBooksActive(
        @RequestParam(required = true) limit: Int,
        @RequestParam(required = true) offset: Int
    ) : ResponseEntity<List<BookResponse>> {
        return ResponseEntity(bookService.getAllActiveBooks(limit, offset).map { it.toResponse() }, HttpStatus.OK)
    }

    @PostMapping
    fun createBook(@RequestBody @Valid request: PostBookRequest) : ResponseEntity<Any> {
        return ResponseEntity(
            bookService.createBook(request.toBook(getCustomerById(request.customerId))),
            HttpStatus.CREATED
        )
    }

    @PutMapping("/{id}")
    fun editBook(@PathVariable id: Int, @RequestBody @Valid request: PutBookRequest) : ResponseEntity<Any> {
        return ResponseEntity(
            bookService.editBook(id, request.toBook(getCustomerById(request.customerId))),
            HttpStatus.NO_CONTENT
        )
    }

    @DeleteMapping("/{id}")
    fun deleteBook(@PathVariable id: Int) : ResponseEntity<Any> {
        return ResponseEntity(bookService.deleteBook(id), HttpStatus.NO_CONTENT)
    }

    private fun getCustomerById(customerId: Int) : Customer {
        return customerService.getCustomerById(customerId)
    }
}