package com.mercadolivro.customers.extension

import com.mercadolivro.customers.Customer
import com.mercadolivro.customers.controller.request.PostCustomerRequest
import com.mercadolivro.customers.controller.request.PutCustomerRequest
import com.mercadolivro.customers.enums.CustomerStatus

fun PostCustomerRequest.toCustomer() : Customer {
    return Customer(
        name = name,
        age = age,
        email = email,
        location = location,
        paymentType = paymentType,
        status = CustomerStatus.ACTIVE
    )
}

fun PutCustomerRequest.toCustomer() : Customer {
    return Customer(
        name = name,
        age = age,
        email = email,
        location = location,
        paymentType = paymentType,
        status = status
    )
}