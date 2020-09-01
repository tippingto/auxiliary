package to.tipping.auxiliary.service

import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.model.ResultDTO

/**
 * @author william
 */
interface SenderService {

    fun send(newsDTO: NewsDTO): Array<ResultDTO>
}