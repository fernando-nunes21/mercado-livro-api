package com.mercadolivro.purcharse.controller

import com.mercadolivro.purcharse.controller.request.PostPurchaseRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/purchase")
class PurchaseController {

    @PostMapping
    fun purchase(@RequestBody request: PostPurchaseRequest) : ResponseEntity<Any> {
        return ResponseEntity(HttpStatus.OK)
    }

}