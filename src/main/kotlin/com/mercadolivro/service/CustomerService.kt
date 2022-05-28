package com.mercadolivro.service

import com.mercadolivro.model.Customer
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Profile
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.enums.ErrorCode
import com.mercadolivro.exceptions.ElementNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
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

    fun getCustomerByEmail(email: String) : Customer {
        return customerRepository.findCustomerByEmail(email)
    }

    fun createCustomer(customer : Customer) {
        val customerToSave = customer.copy(
            role = setOf(Profile.CUSTOMER),
            password = bCryptPasswordEncoder.encode(customer.password)
        )
        customerRepository.save(customerToSave)
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
            paymentType = newCustomer.paymentType ?: previousCustomer.paymentType,
            password = previousCustomer.password
        )
    }

    private fun deletedAllCustomersBooks(customerId: Int) {
        val books = bookService.getAllBooksByCustomerId(customerId)
        books.forEach{
            bookService.deleteBook(it.id!!)
        }
    }
}