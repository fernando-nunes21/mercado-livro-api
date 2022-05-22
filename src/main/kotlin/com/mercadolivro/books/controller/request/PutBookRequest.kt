package com.mercadolivro.customers.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class PutBookRequest(
    var name: String,
    var price: BigDecimal,
    var status : String,

    @JsonProperty("customer_id")
    var customerId : Int

) {
}