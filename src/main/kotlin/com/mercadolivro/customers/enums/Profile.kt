package com.mercadolivro.customers.enums

import jdk.jfr.Description

enum class Profile(var description: String) {
    ADMIN("ADMIN_ROLE"),
    CUSTOMER("CUSTOMER_ROLE")
}