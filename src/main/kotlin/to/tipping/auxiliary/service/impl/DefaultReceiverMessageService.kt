package to.tipping.auxiliary.service.impl

import org.springframework.stereotype.Service
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.service.AfterSendingMessageHandler
import to.tipping.auxiliary.service.BeforeSendingMessageHandler
import to.tipping.auxiliary.service.ReceiverMessageService
import to.tipping.auxiliary.service.SenderService
import to.tipping.auxiliary.utils.LogUtils
import to.tipping.auxiliary.utils.State
import javax.annotation.Resource

/**
 * @author william
 */
@Service
class DefaultReceiverMessageService(

    @Resource
    val senderService: SenderService
) : ReceiverMessageService {

    companion object : LogUtils()

    val defaultAfterSendingMessageHandler = fun(result: ResultDTO) {

        if (result.state === State.FAIL || result.state === State.ERROR) {
            log.error(
                "send message fail, msg id {}, state {}, vendor {}, error code {},  reason {}",
                result.msgId, result.state, result.vendor, result.errorCode, result.reason
            )
        } else {
            log.info(
                "send message success, msg id {}, state {}, vendor {}, platform {}, vendor msg id {}",
                result.msgId, result.state, result.vendor, result.platform, result.vendorMsgId
            )
        }
    }

    override fun send(
        newsDTO: NewsDTO, beforeHandler: BeforeSendingMessageHandler?,
        afterHandler: AfterSendingMessageHandler?
    ): List<ResultDTO> {

        val results: MutableList<ResultDTO> = ArrayList()

        beforeHandler?.before(newsDTO)
        val result = senderService.send(newsDTO)
        results.add(result)

        if (null != afterHandler) {
            afterHandler.after(result)
        } else {
            defaultAfterSendingMessageHandler(result)
        }

        return results
    }
}