package com.mercadolivro.model

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Profile
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
    var status : CustomerStatus? = null,

    @Column
    var password : String,

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "customer_role", joinColumns = [JoinColumn(name = "customer_id")])
    @ElementCollection(targetClass = Profile::class, fetch = FetchType.EAGER)
    var role: Set<Profile> = setOf()

) {
}