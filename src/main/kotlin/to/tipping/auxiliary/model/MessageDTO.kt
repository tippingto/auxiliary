package to.tipping.auxiliary.model

/**
 * @author william
 */
abstract class MessageDTO {

    open val msgId: String? = null

    val router = RouterDTO()

    val body = BodyDTO()
}