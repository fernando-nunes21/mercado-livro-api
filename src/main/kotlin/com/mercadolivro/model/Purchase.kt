package com.mercadolivro.model

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

    @Column
    var nfe : String? = null,

    @Column
    var priceTotal: BigDecimal?,

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    ) {
}