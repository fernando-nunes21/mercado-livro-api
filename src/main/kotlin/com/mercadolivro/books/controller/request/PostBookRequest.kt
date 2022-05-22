package com.mercadolivro.customers.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class PostBookRequest(
    var name: String,
    var price: BigDecimal,

    @JsonProperty("customer_id")
    var customerId : Int

) {
}