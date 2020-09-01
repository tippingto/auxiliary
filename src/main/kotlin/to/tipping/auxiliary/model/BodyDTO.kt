package to.tipping.auxiliary.model

/**
 * @author william
 */
data class BodyDTO(
    var content: String = ""
) {
    var title: String? = null
    val encodedTitle: String? = null
    val encodedContent: String? = null
    val templateId: String? = null
    val params: Map<String, String>? = null
}