package com.mercadolivro.customers.controller.request

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.mercadolivro.customers.enums.CustomerStatus
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
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
    var paymentType : String
) {
}