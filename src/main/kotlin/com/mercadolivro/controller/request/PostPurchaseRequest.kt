package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.NotNull

data class PostPurchaseRequest(

    @field:NotNull
    @JsonProperty(value = "customer_id")
    var customerId : Int,

    @field:NotNull
    @JsonProperty(value = "books_ids")
    var booksIds : List<Int>

) {
}