package com.mercadolivro.generic.exceptions

class ElementNotFoundException(override var message : String, var errorCode: String) : Exception() {

}