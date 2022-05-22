package com.mercadolivro.customers

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "Customers")
data class Customer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null,

    @Column
    var name : String,

    @Column
    var age : Int,

    @Column
    var email : String,

    @Column
    var location : String,

    @Column(name = "payment_type")
    var paymentType : String

) {
}