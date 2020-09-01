package to.tipping.auxiliary.service.vendor

import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.model.VendorDTO
import to.tipping.auxiliary.utils.Type

/**
 * @author william
 */
interface VendorHandler {

    fun assemble(messageDTO: MessageDTO): VendorDTO

    fun send(vendorDTO: VendorDTO, messageDTO: MessageDTO): ResultDTO

    fun isOnline(): Boolean

    fun type(): Type
}