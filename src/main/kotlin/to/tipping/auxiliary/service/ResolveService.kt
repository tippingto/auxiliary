package to.tipping.auxiliary.service

import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.ResultDTO

/**
 * @author william
 */
interface ResolveService {

//    fun resolve(messageDTO: MessageDTO): List<VendorBuilder<VendorDTO>>

    fun resolve(messageDTO: MessageDTO)

    fun updateResult(historyId: Long, resultDTO: ResultDTO)
}