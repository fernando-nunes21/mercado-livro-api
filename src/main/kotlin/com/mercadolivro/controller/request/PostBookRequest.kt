package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

data class PostBookRequest(

    @field:NotEmpty
    var name: String,

    @field:NotNull
    var price: BigDecimal,

    @field:NotNull
    @JsonProperty("customer_id")
    var customerId : Int

) {
}