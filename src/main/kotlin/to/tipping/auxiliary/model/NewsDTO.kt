package to.tipping.auxiliary.model

import java.util.*

data class NewsDTO(
    val tos: String = "",
    val content: String = "",
    val type: Int = 0
) {
    val nativeCode: String? = null
    val templateId: String? = null
    val subject: String? = null
    val params: HashMap<String, String>? = null
}