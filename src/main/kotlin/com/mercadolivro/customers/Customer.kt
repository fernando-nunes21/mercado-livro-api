package com.mercadolivro.customers

import com.mercadolivro.customers.enums.CustomerStatus
import javax.persistence.*

@Entity(name = "Customers")
data class Customer(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null,

    @Column
    var name : String?,

    @Column
    var age : Int?,

    @Column
    var email : String?,

    @Column
    var location : String?,

    @Column(name = "payment_type")
    var paymentType : String?,

    @Column
    @Enumerated(EnumType.STRING)
    var status : CustomerStatus? = null

) {
}