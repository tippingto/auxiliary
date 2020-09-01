package to.tipping.auxiliary.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import to.tipping.auxiliary.model.GenericResponse
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.service.MessageService
import javax.annotation.Resource

/**
 * @author william
 */
@RestController
@RequestMapping("/message")
class MessageController(

    @Resource
    var messageService: MessageService
) {

    @PostMapping(value = ["/send"])
    fun send(@RequestBody request: NewsDTO): GenericResponse<Array<ResultDTO>> {
        val response = GenericResponse<Array<ResultDTO>>(true)
        response.data = messageService.send(request)
        return response
    }
}