package com.mercadolivro.customers.controller

import com.mercadolivro.customers.controller.request.PostCustomerRequest
import com.mercadolivro.customers.controller.request.PutCustomerRequest
import com.mercadolivro.customers.controller.response.CustomerResponse
import com.mercadolivro.customers.extension.toCustomer
import com.mercadolivro.customers.extension.toResponse
import com.mercadolivro.customers.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("v1/customers")
class CustomerController(
    val customerService: CustomerService
) {

    @GetMapping
    fun getCustomers(
        @RequestParam name: String?,
        @RequestParam(required = true) offset: Int,
        @RequestParam(required = true) limit: Int
    ) : ResponseEntity<List<CustomerResponse>> {
        return ResponseEntity(customerService.getCustomers(name, offset, limit).map { it.toResponse() }, HttpStatus.OK)
    }

    @GetMapping("/{id}")
    fun getCustomerById(@PathVariable id : Int) : ResponseEntity<CustomerResponse> {
        return ResponseEntity(customerService.getCustomerById(id).toResponse(), HttpStatus.OK)
    }

    @PostMapping
    fun createCustomer(@RequestBody @Valid request: PostCustomerRequest) : ResponseEntity<Any>{
        customerService.createCustomer(request.toCustomer())
        return ResponseEntity(HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun editCustomer(@PathVariable id : String, @RequestBody @Valid request: PutCustomerRequest) : ResponseEntity<Any> {
        customerService.editCustomer(id, request.toCustomer())
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id : String) : ResponseEntity<Any> {
        customerService.deleteCustomer(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}