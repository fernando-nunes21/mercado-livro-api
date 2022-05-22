package com.mercadolivro.customers.service

import com.mercadolivro.books.service.BookService
import com.mercadolivro.customers.Customer
import com.mercadolivro.customers.enums.CustomerStatus
import com.mercadolivro.customers.repository.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.persistence.Id

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
        val customer = customerRepository.findByIdOrNull(id = id.toInt())
        if(customer != null) {
            return customer
        } else {
            throw Exception("Customer not found by informed id")
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
            throw Exception("Customer not found by informed id")
        }
    }

    fun deleteCustomer(id : String) {
        if (customerRepository.existsById(id.toInt())){
            val customer = getCustomerById(id.toInt())
            customer.status = CustomerStatus.INACTIVE
            deletedAllCustomersBooks(customer.id!!)
            customerRepository.save(customer)
        } else {
            throw Exception("Customer not found by informed id")
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