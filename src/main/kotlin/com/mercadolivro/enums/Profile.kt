package com.mercadolivro.enums

import jdk.jfr.Description

enum class Profile(var description: String) {
    ADMIN("ROLE_ADMIN"),
    CUSTOMER("ROLE_CUSTOMER")
}