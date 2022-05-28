package com.mercadolivro.extension

import com.mercadolivro.model.Customer
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.enums.CustomerStatus

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

