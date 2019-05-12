package com.github.augustovictor.springpactconsumerdrivencontract

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class SpringPactConsumerDrivenContractApplication

fun main(args: Array<String>) {
	runApplication<SpringPactConsumerDrivenContractApplication>(*args)
}
