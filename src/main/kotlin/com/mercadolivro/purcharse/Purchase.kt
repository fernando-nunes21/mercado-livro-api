package com.mercadolivro.purcharse

import com.mercadolivro.customers.Customer
import java.awt.print.Book
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "Purchases")
data class Purchase(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer : Customer,

    @ManyToMany
    @JoinTable(
        name = "purchase_book",
        joinColumns = [JoinColumn(name = "purchase_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")]
    )
    var books : List<Book>,

    var nfe : String,
    var priceTotal: BigDecimal,
    var createdAt: LocalDateTime

    ) {
}