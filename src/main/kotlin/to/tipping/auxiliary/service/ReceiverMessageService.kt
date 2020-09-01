package to.tipping.auxiliary.service

import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.model.ResultDTO

/**
 * @author william
 */
interface ReceiverMessageService {

    fun send(
        newsDTO: NewsDTO, beforeHandler: BeforeSendingMessageHandler?,
        afterHandler: AfterSendingMessageHandler?
    ): List<ResultDTO>
}