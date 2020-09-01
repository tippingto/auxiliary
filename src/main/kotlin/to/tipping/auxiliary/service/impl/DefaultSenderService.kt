package to.tipping.auxiliary.service.impl

import org.springframework.stereotype.Service
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.service.ComposeService
import to.tipping.auxiliary.service.SenderService
import to.tipping.auxiliary.service.vendor.VendorHandler
import to.tipping.auxiliary.utils.LogUtils
import to.tipping.auxiliary.utils.Type
import javax.annotation.Resource

/**
 * @author william
 */
@Service
class DefaultSenderService(

    @Resource
    val vendorHandlers: List<VendorHandler>,

    @Resource
    val composeServices: List<ComposeService>

) : SenderService {

    companion object : LogUtils()

    override fun send(newsDTO: NewsDTO): Array<ResultDTO> {
        val newsType = Type.forValue(newsDTO.type)

        var composeService: ComposeService? = null
        var vendorHandler: VendorHandler? = null

        var results = emptyArray<ResultDTO>()

        for (_composeService in composeServices) {
            if (_composeService.type() === newsType) {
                composeService = _composeService
                break
            }
        }

        for (_vendorHandler in vendorHandlers) {
            if (_vendorHandler.type() === newsType && _vendorHandler.isOnline()) {
                vendorHandler = _vendorHandler
                break
            }
        }

        if (null != composeService && null != vendorHandler) {
            val messageDTO = composeService.compose(newsDTO)
            val vendorDTO = vendorHandler.assemble(messageDTO)

//            TODO("save message to db")

            results = vendorHandler.send(vendorDTO, messageDTO)

//            TODO("update message info in db")
        }

        log.info("message results: {}", results)

        return results
    }
}