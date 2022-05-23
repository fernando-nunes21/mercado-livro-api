package com.mercadolivro.customers.controller.response

import com.mercadolivro.customers.enums.CustomerStatus

data class CustomerResponse(

    var id : Int? = null,
    var name : String?,
    var age : Int?,
    var email : String?,
    var location : String?,
    var paymentType : String?,
    var status : CustomerStatus? = null

) {
}