package to.tipping.auxiliary.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import to.tipping.auxiliary.model.GenericResponse

/**
 * @author william
 */
class MessageControllerTest {
    val objectMapper = ObjectMapper()
    val host = "https://dev-api.tipping.to/auxiliary/message/send"

    @Test
    fun `Send mail to tom`() {
        val restTemplate = RestTemplate()
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val map: HashMap<String, Any> = HashMap()
        map["tos"] = "tombluemoon@gmail.com"
        map["content"] = "test"
        map["type"] = 2
        map["subject"] = "testetest"

        val request = HttpEntity(objectMapper.writeValueAsString(map), headers)

        val result = restTemplate.postForObject(host, request, GenericResponse::class.java)
        result?.success?.let { Assertions.assertTrue(it) }
    }
}