package to.tipping.auxiliary.service.vendor

import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.model.VendorDTO
import to.tipping.auxiliary.utils.State

/**
 * @author william
 */
abstract class AbstractVendorHandler : VendorHandler {

    protected fun composeResultDTO(vendorDTO: VendorDTO, messageDTO: MessageDTO): ResultDTO {
        val resultDTO = ResultDTO()
        resultDTO.msgId = messageDTO.msgId
        resultDTO.vendor = vendorDTO.vendor
        resultDTO.type = messageDTO.router.type
        return resultDTO
    }

    protected fun handleException(resultDTO: ResultDTO, e: Exception) {
        resultDTO.errorCode = -99
        resultDTO.reason = e.localizedMessage
        resultDTO.state = State.ERROR
    }
}