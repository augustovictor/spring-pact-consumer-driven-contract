package com.github.augustovictor.springpactconsumerdrivencontract.jsonplaceholder

import com.github.augustovictor.springpactconsumerdrivencontract.user.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "JsonPlaceholder", url = "\${app.external.client.url}")
interface JsonPlaceholderClient {
    @GetMapping(path = ["/users"])
    fun fetchUsers(): List<User>
}