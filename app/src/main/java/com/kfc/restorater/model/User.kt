package com.kfc.restorater.model

class User {
    var email: String? = null
    var password: String? = null

    constructor(email: String?, password: String?) {
        this.email = email
        this.password = password
    }

    constructor()

    fun getEmail(): String? {
        return email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPassword(): String? {
        return password
    }

    fun setPassword(password: String?) {
        this.password = password
    }

    override fun toString(): String {
        return "User(email=$email, password=$password)"
    }

}