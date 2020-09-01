package to.tipping.auxiliary.model

import to.tipping.auxiliary.utils.Type
import java.util.*

/**
 * @author william
 */
class MailMessageDTO(
    override val msgId: String = UUID.randomUUID().toString()
) : MessageDTO() {
    init {
        router.type = Type.MAIL
    }
}