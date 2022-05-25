package com.mercadolivro.customers.service

import com.mercadolivro.books.service.BookService
import com.mercadolivro.customers.Customer
import com.mercadolivro.customers.enums.CustomerStatus
import com.mercadolivro.customers.repository.CustomerRepository
import com.mercadolivro.generic.enums.ErrorCode
import com.mercadolivro.generic.exceptions.ElementNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    fun getCustomers(name: String?, offset: Int, limit: Int) : List<Customer> {
        return if(name != null){
            customerRepository.findByNameWithPagination(name, limit, offset)
        } else {
            customerRepository.findAllCustomersWithPagination(limit, offset)
        }
    }

    fun getCustomerById(id: Int) : Customer {
        return customerRepository.findById(id).orElseThrow{
            ElementNotFoundException(message = ErrorCode.M0001.message, errorCode = ErrorCode.M0001.errorCode)
        }
    }

    fun createCustomer(customer : Customer) {
        customerRepository.save(customer)
    }

    fun editCustomer(id : String, customer : Customer){
        if (customerRepository.existsById(id.toInt())){
            val previousCustomer = getCustomerById(id.toInt())
            customerRepository.save(buildCustomerToSave(previousCustomer, customer))
        } else {
            throw ElementNotFoundException(message = ErrorCode.M0001.message, errorCode = ErrorCode.M0001.errorCode)
        }
    }

    fun deleteCustomer(id : String) {
        if (customerRepository.existsById(id.toInt())){
            val customer = getCustomerById(id.toInt())
            customer.status = CustomerStatus.INACTIVE
            deletedAllCustomersBooks(customer.id!!)
            customerRepository.save(customer)
        } else {
            throw ElementNotFoundException(message = ErrorCode.M0001.message, errorCode = ErrorCode.M0001.errorCode)
        }
    }

    private fun buildCustomerToSave(previousCustomer: Customer, newCustomer: Customer) : Customer {
        return Customer(
            id = newCustomer.id ?: previousCustomer.id,
            name = newCustomer.name ?: previousCustomer.name ,
            age = newCustomer.age ?: previousCustomer.age,
            email = newCustomer.email ?: previousCustomer.email,
            location = newCustomer.location ?: previousCustomer.location,
            paymentType = newCustomer.paymentType ?: previousCustomer.paymentType
        )
    }

    private fun deletedAllCustomersBooks(customerId: Int) {
        val books = bookService.getAllBooksByCustomerId(customerId)
        books.forEach{
            bookService.deleteBook(it.id!!)
        }
    }
}