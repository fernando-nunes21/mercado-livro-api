package com.mercadolivro.exceptions

class ElementNotFoundException(override var message : String, var errorCode: String) : Exception() {

}