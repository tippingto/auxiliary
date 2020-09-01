package to.tipping.auxiliary.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import to.tipping.auxiliary.model.GenericResponse
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.service.ReceiverMessageService
import javax.annotation.Resource

/**
 * @author william
 */
@Controller
@RequestMapping("/message")
class MessageController(

    @Resource
    var receiverMessageService: ReceiverMessageService
) {

    @PostMapping(value = ["/send"])
    @Throws(Exception::class)
    fun send(@RequestBody request: NewsDTO): GenericResponse<String> {
        val response = GenericResponse<String>(true)
        receiverMessageService.send(request, null, null)
        return response
    }
}