package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
data class PostCustomerRequest(

    @field:NotEmpty
    var name : String,

    @field:NotNull
    var age : Int,

    @field:NotEmpty
    @field:Email
    var email : String,

    @field:NotEmpty
    var location : String,

    @field:NotEmpty
    @JsonProperty("payment_type")
    var paymentType : String,

    @field:NotEmpty
    var password: String

) {
}