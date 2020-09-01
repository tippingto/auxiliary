package to.tipping.auxiliary.service.vendor.mail

import to.tipping.auxiliary.service.vendor.AbstractVendorHandler
import to.tipping.auxiliary.utils.LogUtils
import to.tipping.auxiliary.utils.Type

/**
 * @author william
 */
abstract class MailVendorHandler : AbstractVendorHandler() {

    companion object : LogUtils()

    override fun type(): Type {
        return Type.MAIL
    }

    override fun isOnline(): Boolean {
        return false
    }
}

