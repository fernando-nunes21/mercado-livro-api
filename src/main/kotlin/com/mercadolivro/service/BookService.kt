package com.mercadolivro.service

import com.mercadolivro.model.Book
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.enums.ErrorCode
import com.mercadolivro.exceptions.ElementNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookService(
   private val bookRepository: BookRepository
) {

    fun getAllBooks(name : String?, limit: Int, offset: Int) : List<Book> {
        return if(name != null) {
            bookRepository.findByNameWithPagination(name, limit, offset)
        } else {
            bookRepository.findAllBooksWithPagination(limit, offset)
        }
    }

    fun getBookById(id: Int) : Book {
        return bookRepository.findById(id).orElseThrow {
            ElementNotFoundException(ErrorCode.M0101.message, ErrorCode.M0101.errorCode)
        }
    }

    fun getAllActiveBooks(limit: Int, offset: Int) : List<Book>{
        return bookRepository.findAllActiveBooks(limit, offset)
    }

    fun getAllBooksByCustomerId(customerId: Int) : List<Book> {
        return bookRepository.findAllBooksByCustomerId(customerId)
    }

    fun getAllBooksByIds(booksIds : List<Int>) : List<Book> {
        return bookRepository.getAllBooksByIds(booksIds)
    }

    fun createBook(book : Book) {
        bookRepository.save(book)
    }

    fun editBook(id: Int, book: Book) {
        if(bookRepository.existsById(id)){
            val previousBook = getBookById(id)
            bookRepository.save(buildBookToSave(previousBook, book))
        } else {
            throw ElementNotFoundException(ErrorCode.M0101.message, ErrorCode.M0101.errorCode)
        }
    }

    fun deleteBook(id: Int) {
        if(bookRepository.existsById(id)){
            val book = bookRepository.findByIdOrNull(id)
            book?.status = BookStatus.DELETED
            bookRepository.save(book!!)
        } else {
            throw ElementNotFoundException(ErrorCode.M0101.message, ErrorCode.M0101.errorCode)
        }
    }

    fun updatePurchaseBook(books : List<Book>) {
        books.forEach{
            it.status = BookStatus.SOLD
            bookRepository.save(it)
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