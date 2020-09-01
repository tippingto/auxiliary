package to.tipping.auxiliary.service

import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.model.ResultDTO

/**
 * @author william
 */
interface MessageService {

    fun send(
        newsDTO: NewsDTO, beforeHandler: BeforeSendingMessageHandler? = null,
        afterHandler: AfterSendingMessageHandler? = null
    ): Array<ResultDTO>
}