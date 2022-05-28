package com.mercadolivro.service

import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.model.UserCustomDetails
import org.apache.tomcat.websocket.AuthenticationException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserCustomDetailsService(
    private val customerRepository: CustomerRepository
) : UserDetailsService {
    override fun loadUserByUsername(id: String?): UserDetails {
        val customer = customerRepository.findById(id!!.toInt()).orElseThrow { AuthenticationException("Deu Ruim") }
        return UserCustomDetails(customer)
    }
}