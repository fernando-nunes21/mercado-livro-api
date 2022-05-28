package com.mercadolivro.model

import com.mercadolivro.enums.BookStatus
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "Books")
data class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String?,

    @Column
    var price: BigDecimal?,


    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null

) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if(field == BookStatus.DELETED || field == BookStatus.CANCELED)
                throw Exception("Can't alter status of a DELETED or CANCELED book")
            field = value
        }

    constructor(
        id: Int? = null,
        name: String?,
        price: BigDecimal?,
        status: BookStatus?,
        customer: Customer? = null) : this(id,name,price,customer) {
            this.status = status
        }
}