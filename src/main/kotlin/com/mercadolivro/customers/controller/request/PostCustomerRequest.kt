package com.mercadolivro.customers.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.mercadolivro.customers.enums.CustomerStatus

data class PostCustomerRequest(
    var name : String,
    var age : Int,
    var email : String,
    var location : String,

    @JsonProperty("payment_type")
    var paymentType : String,
    var status : CustomerStatus
) {
}