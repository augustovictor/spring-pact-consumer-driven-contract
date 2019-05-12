package com.github.augustovictor.springpactconsumerdrivencontract

import au.com.dius.pact.consumer.Pact
import au.com.dius.pact.consumer.PactProviderRuleMk2
import au.com.dius.pact.consumer.PactVerification
import au.com.dius.pact.consumer.dsl.PactDslWithProvider
import au.com.dius.pact.model.RequestResponsePact
import com.github.augustovictor.springpactconsumerdrivencontract.jsonplaceholder.JsonPlaceholderClient
import org.apache.http.entity.ContentType
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(properties = [
     "account-service.ribbon.listOfServers: localhost:8092",
    "account-service.ribbon.eureka.enabled: false",
    "eureka.client.enabled: false"
])
class JsonPlaceholderClientTest {

    @Autowired
    protected lateinit var jsonPlaceholderClient: JsonPlaceholderClient

    @get:Rule
    val jsonPlaceholderProvider = PactProviderRuleMk2("jsonplaceholderprovider", "localhost", 8092, this)

    @Pact(state = "list-of-users", provider = "jsonplaceholderprovider", consumer = "test_consumer")
    fun createPact(builder: PactDslWithProvider): RequestResponsePact {
        val pact = builder
                .given("list-of-users").uponReceiving("a request for all users")
                .path("/users").method("GET").willRespondWith().status(200)
                .body(
                        "{[{\"id\":1,\"name\":\"Leanne Graham\",\"username\":\"Bret\",\"phone\":\"1-770-736-8031 x56442\"},{\"id\":2,\"name\":\"Ervin Howell\",\"username\":\"Antonette\",\"phone\":\"010-692-6593 x09125\"},{\"id\":3,\"name\":\"Clementine Bauch\",\"username\":\"Samantha\",\"phone\":\"1-463-123-4447\"},{\"id\":4,\"name\":\"Patricia Lebsack\",\"username\":\"Karianne\",\"phone\":\"493-170-9623 x156\"},{\"id\":5,\"name\":\"Chelsey Dietrich\",\"username\":\"Kamren\",\"phone\":\"(254)954-1289\"},{\"id\":6,\"name\":\"Mrs. Dennis Schulist\",\"username\":\"Leopoldo_Corkery\",\"phone\":\"1-477-935-8478 x6430\"},{\"id\":7,\"name\":\"Kurtis Weissnat\",\"username\":\"Elwyn.Skiles\",\"phone\":\"210.067.6132\"},{\"id\":8,\"name\":\"Nicholas Runolfsdottir V\",\"username\":\"Maxime_Nienow\",\"phone\":\"586.493.6943 x140\"},{\"id\":9,\"name\":\"Glenna Reichert\",\"username\":\"Delphine\",\"phone\":\"(775)976-6794 x41206\"},{\"id\":10,\"name\":\"Clementina DuBuque\",\"username\":\"Moriah.Stanton\",\"phone\":\"024-648-3804\"}]}",
                        ContentType.APPLICATION_JSON
                ).toPact()
        return pact
    }

    @Test
    @PactVerification(fragment = "createPact")
    fun `should fetch users from jsonplaceholder api`() {
        val usersResponse = jsonPlaceholderClient.fetchUsers()
        assertEquals(1, usersResponse[0].id)
    }
}