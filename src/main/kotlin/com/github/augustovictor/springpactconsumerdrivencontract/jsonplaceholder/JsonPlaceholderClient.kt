package com.github.augustovictor.springpactconsumerdrivencontract.jsonplaceholder

import com.github.augustovictor.springpactconsumerdrivencontract.user.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "JsonPlaceholder", url = "http://localhost:3000")
interface JsonPlaceholderClient {
    @GetMapping(path = ["/external/users"])
    fun fetchUsers(): List<User>
}