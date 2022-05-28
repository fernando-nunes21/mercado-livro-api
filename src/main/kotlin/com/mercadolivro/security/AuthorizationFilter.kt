package com.mercadolivro.security

import com.mercadolivro.service.UserCustomDetailsService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.naming.AuthenticationException
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userCustomDetailsService: UserCustomDetailsService
): BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        var auth = request.getHeader("Authorization")
        if (auth != null && auth.startsWith("Bearer")) {
            val authentication = getAuthentication(auth.split(" ")[1])
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }

    private fun getAuthentication(token: String): UsernamePasswordAuthenticationToken {
        if(!jwtUtils.isValidToken(token)) {
            throw AuthenticationException("Invalid Token")
        } else {
            val subject = jwtUtils.getSubject(token)
            val customer = userCustomDetailsService.loadUserByUsername(subject)
            return UsernamePasswordAuthenticationToken(customer, null, customer.authorities)
        }
    }
}