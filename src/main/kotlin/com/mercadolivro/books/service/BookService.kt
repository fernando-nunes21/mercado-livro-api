package com.mercadolivro.books.service

import com.mercadolivro.books.Book
import com.mercadolivro.books.repository.BookRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {

    fun getAllBooks(name : String?, limit: Int, offset: Int) : List<Book> {
        return if(name != null) {
            bookRepository.findByNameWithPagination(name, limit, offset)
        } else {
            bookRepository.findAllBooksWithPagination(limit, offset)
        }
    }

    fun getBookById(id: Int) : Book{
        val book = bookRepository.findByIdOrNull(id)
        if(book != null) {
            return book
        } else {
            throw Exception("Book id was not found")
        }
    }

    fun createBook(book : Book) {
        bookRepository.save(book)
    }

    fun editBook(id: Int, book: Book) {
        if(bookRepository.existsById(id)){
            book.id = id
            bookRepository.save(book)
        } else {
            throw Exception("Book id was not found on DB")
        }
    }

    fun deleteBook(id: Int) {
        if(bookRepository.existsById(id)){
            bookRepository.deleteById(id)
        } else {
            throw Exception("Book id was not found on DB")
        }
    }

}