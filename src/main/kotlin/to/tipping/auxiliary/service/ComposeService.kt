package to.tipping.auxiliary.service

import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.utils.Type

/**
 * @author william
 */
interface ComposeService {

    fun compose(newsDTO: NewsDTO): MessageDTO

    fun type(): Type
}