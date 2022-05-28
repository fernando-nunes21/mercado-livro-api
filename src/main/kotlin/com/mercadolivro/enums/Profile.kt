package com.mercadolivro.enums

import jdk.jfr.Description

enum class Profile(var description: String) {
    ADMIN("ADMIN_ROLE"),
    CUSTOMER("CUSTOMER_ROLE")
}