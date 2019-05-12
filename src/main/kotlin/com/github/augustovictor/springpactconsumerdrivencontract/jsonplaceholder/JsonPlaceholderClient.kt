package com.github.augustovictor.springpactconsumerdrivencontract.jsonplaceholder

import com.github.augustovictor.springpactconsumerdrivencontract.user.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "JsonPlaceholder", url = "http://localhost:8092/users")
interface JsonPlaceholderClient {
    @GetMapping
    fun fetchUsers(): List<User>
}