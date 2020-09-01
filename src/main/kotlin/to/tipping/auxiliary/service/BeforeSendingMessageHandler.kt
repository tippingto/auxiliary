package to.tipping.auxiliary.service

import to.tipping.auxiliary.model.NewsDTO

/**
 * @author william
 */
interface BeforeSendingMessageHandler {

    fun before(newsDTO: NewsDTO)
}