package to.tipping.auxiliary.model

import to.tipping.auxiliary.utils.Platform
import to.tipping.auxiliary.utils.Type

/**
 * @author william
 */
data class RouterDTO(
    // 1:sms; 2: mail; 3: push message
    var type: Type = Type.NONE,
    // 1: iOS; 2: android
    var platform: Platform = Platform.UNKNOWN,
    // array split by "|"
    var tos: String = ""
) {

    var nativeCode: String? = null
}