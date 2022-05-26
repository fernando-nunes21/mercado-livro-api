package com.mercadolivro.customers.extension

import com.mercadolivro.customers.Customer
import com.mercadolivro.customers.controller.request.PostCustomerRequest
import com.mercadolivro.customers.controller.request.PutCustomerRequest
import com.mercadolivro.customers.controller.response.CustomerResponse
import com.mercadolivro.customers.enums.CustomerStatus

fun PostCustomerRequest.toCustomer() : Customer {
    return Customer(
        name = name,
        age = age,
        email = email,
        location = location,
        paymentType = paymentType,
        status = CustomerStatus.ACTIVE,
        password = password
    )
}

fun PutCustomerRequest.toCustomer(previousCustomer: Customer) : Customer {
    return Customer(
        name = name,
        age = age,
        email = email,
        location = location,
        paymentType = paymentType,
        status = status,
        password = previousCustomer.password
    )
}

fun Customer.toResponse() : CustomerResponse {
    return CustomerResponse(
        id = id,
        name = name,
        age = age,
        email = email,
        location = location,
        paymentType = paymentType,
        status = status
    )
}

