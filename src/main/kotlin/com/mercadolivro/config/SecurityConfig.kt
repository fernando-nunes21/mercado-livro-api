package com.mercadolivro.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    private val routes = arrayOf(
        "/v1/customers"
    )

    override fun configure(http : HttpSecurity) {
        http.cors().and().csrf().disable()
        http.authorizeRequests()
            .antMatchers(HttpMethod.POST, *routes).permitAll()
            .anyRequest().authenticated()
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bCryptPasswordEncoder() : BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}