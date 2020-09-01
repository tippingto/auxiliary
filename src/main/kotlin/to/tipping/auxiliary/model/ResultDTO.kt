package to.tipping.auxiliary.model

import to.tipping.auxiliary.utils.Platform
import to.tipping.auxiliary.utils.State
import to.tipping.auxiliary.utils.Type
import to.tipping.auxiliary.utils.Vendor

/**
 * @author william
 */
data class ResultDTO(

    var success: Boolean = false,
    var state: State = State.UNKNOWN,
    var type: Type = Type.NONE,
    var vendor: Vendor = Vendor.UNKNOWN,
    var platform: Platform = Platform.UNKNOWN
) {
    var msgId: String? = null
    var vendorMsgId: String? = null
    var errorCode: Int? = null
    var reason: String? = null
    var token: String? = null
    var deviceId: String? = null
}