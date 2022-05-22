package com.mercadolivro.customers.service

import com.mercadolivro.customers.Customer
import com.mercadolivro.customers.repository.CustomerRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    fun getCustomers(name: String?, offset: Int, limit: Int) : List<Customer> {
        return if(name != null){
            customerRepository.findByNameWithPagination(name, limit, offset)
        } else {
            customerRepository.findAllCustomersWithPagination(limit, offset)
        }
    }

    fun getCustomerById(id : String) : Customer {
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
            val customerToSave = Customer(
                id.toInt(), customer.name, customer.age, customer.email, customer.location, customer.paymentType
            )
            customerRepository.save(customerToSave)
        } else {
            throw Exception("Customer not found by informed id")
        }
    }

    fun deleteCustomer(id : String) {
        if (customerRepository.existsById(id.toInt())){
            customerRepository.deleteById(id.toInt())
        } else {
            throw Exception("Customer not found by informed id")
        }
    }
}