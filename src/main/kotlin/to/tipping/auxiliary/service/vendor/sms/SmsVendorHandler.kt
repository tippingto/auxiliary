package to.tipping.auxiliary.service.vendor.sms

import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.service.vendor.VendorHandler
import to.tipping.auxiliary.utils.Type

/**
 * @author william
 */
abstract class SmsVendorHandler : VendorHandler {

    abstract fun isDefault(): Boolean

    abstract fun isValidNativeCode(messageDTO: MessageDTO?): Boolean

    override fun type(): Type {
        return Type.SMS
    }

    override fun isOnline(): Boolean {
        return false
    }
}