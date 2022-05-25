package com.mercadolivro.purcharse.controller

import com.mercadolivro.purcharse.controller.request.PostPurchaseRequest
import com.mercadolivro.purcharse.mapper.PurchaseMapper
import com.mercadolivro.purcharse.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/purchase")
class PurchaseController(
    val purchaseMapper: PurchaseMapper,
    val purchaseService: PurchaseService
) {

    @PostMapping
    fun purchase(@RequestBody request: PostPurchaseRequest) : ResponseEntity<Any> {
        return ResponseEntity(purchaseService.purchase(purchaseMapper.toModel(request)), HttpStatus.OK)
    }

}