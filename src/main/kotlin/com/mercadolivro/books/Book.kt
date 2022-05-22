package com.mercadolivro.books

import com.mercadolivro.books.enums.BookStatus
import com.mercadolivro.customers.Customer
import java.math.BigDecimal
import javax.persistence.*

@Entity
data class Book(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null

) {
}