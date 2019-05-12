package com.github.augustovictor.springpactconsumerdrivencontract.user

import com.github.augustovictor.springpactconsumerdrivencontract.jsonplaceholder.JsonPlaceholderClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class Controller(private val jsonPlaceholderClient: JsonPlaceholderClient) {
    @GetMapping
    fun listUsers(): List<User> = jsonPlaceholderClient.fetchUsers()
}