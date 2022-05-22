package com.mercadolivro.books.service

import com.mercadolivro.books.Book
import com.mercadolivro.books.enums.BookStatus
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

    fun getAllActiveBooks(limit: Int, offset: Int) : List<Book>{
        return bookRepository.findAllActiveBooks(limit, offset)
    }

    fun getAllBooksByCustomerId(customerId: Int) : List<Book> {
        return bookRepository.findAllBooksByCustomerId(customerId)
    }

    fun createBook(book : Book) {
        bookRepository.save(book)
    }

    fun editBook(id: Int, book: Book) {
        if(bookRepository.existsById(id)){
            val previousBook = getBookById(id)
            bookRepository.save(buildBookToSave(previousBook, book))
        } else {
            throw Exception("Book id was not found on DB")
        }
    }

    fun deleteBook(id: Int) {
        if(bookRepository.existsById(id)){
            val book = bookRepository.findByIdOrNull(id)
            book?.status = BookStatus.DELETED
            bookRepository.save(book!!)
        } else {
            throw Exception("Book id was not found on DB")
        }
    }

    private fun buildBookToSave(previousBook: Book, newBook: Book) : Book {
        return Book(
            id = newBook.id ?: previousBook.id,
            name = newBook.name ?: previousBook.name,
            price = newBook.price ?: previousBook.price,
            status = newBook.status ?: previousBook.status,
            customer = newBook.customer ?: previousBook.customer
        )
    }

}