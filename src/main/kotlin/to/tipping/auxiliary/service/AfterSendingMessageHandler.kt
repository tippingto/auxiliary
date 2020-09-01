package to.tipping.auxiliary.service

import to.tipping.auxiliary.model.ResultDTO

/**
 * @author william
 */
interface AfterSendingMessageHandler {

    fun after(results: Array<ResultDTO>)
}