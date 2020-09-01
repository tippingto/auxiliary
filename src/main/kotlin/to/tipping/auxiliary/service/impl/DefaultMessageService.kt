package to.tipping.auxiliary.service.impl

import org.springframework.stereotype.Service
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.service.AfterSendingMessageHandler
import to.tipping.auxiliary.service.BeforeSendingMessageHandler
import to.tipping.auxiliary.service.MessageService
import to.tipping.auxiliary.service.SenderService
import to.tipping.auxiliary.utils.LogUtils
import to.tipping.auxiliary.utils.State
import javax.annotation.Resource

/**
 * @author william
 */
@Service
class DefaultMessageService(

    @Resource
    val senderService: SenderService
) : MessageService {

    companion object : LogUtils()

    val defaultAfterSendingMessageHandler = fun(results: Array<ResultDTO>) {

        for (result in results) {
            if (result.state === State.FAIL || result.state === State.ERROR) {
                log.error(
                    "send message fail, msg id {}, track id {}, state {}, vendor {}, error code {},  reason {}",
                    result.msgId, result.trackId, result.state, result.vendor, result.errorCode, result.reason
                )
            } else {
                log.info(
                    "send message success, msg id {}, track id {}, state {}, vendor {}, platform {}, vendor msg id {}",
                    result.msgId, result.trackId, result.state, result.vendor, result.platform, result.vendorMsgId
                )
            }
        }
    }

    override fun send(
        newsDTO: NewsDTO, beforeHandler: BeforeSendingMessageHandler?,
        afterHandler: AfterSendingMessageHandler?
    ): Array<ResultDTO> {

        beforeHandler?.before(newsDTO)

        val results = senderService.send(newsDTO)

        if (null != afterHandler) {
            afterHandler.after(results)
        } else {
            defaultAfterSendingMessageHandler(results)
        }

        return results
    }
}