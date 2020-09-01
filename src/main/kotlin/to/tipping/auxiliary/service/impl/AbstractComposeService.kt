package to.tipping.auxiliary.service.impl

import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.service.ComposeService
import to.tipping.auxiliary.utils.LogUtils

abstract class AbstractComposeService : ComposeService {
    //    @Resource
    //    private NotificationTemplatesDAO notificationTemplatesDAO;

    companion object : LogUtils();

    override fun compose(newsDTO: NewsDTO): MessageDTO {
        val messageDTO = create(newsDTO)
        messageDTO.router.tos = newsDTO.tos
        log.info(
            "create message id: {}, type: {}, title: {}, content: {}",
            messageDTO.msgId, messageDTO.router.type, messageDTO.body.title, messageDTO.body.content
        )
        return messageDTO
    }

    //@Cacheable(key = "'ntf.templates.' + #key",value = "ntf.templates")
    //    @Transactional(readOnly = true)
    //    public NotificationTemplates getTemplate(String key){
    //        NotificationTemplates templates = new NotificationTemplates();
    //        templates.setKey(key);
    //        return notificationTemplatesDAO.findOne(Example.of(templates)).orElseGet(null);
    //    }
    abstract fun create(newsDTO: NewsDTO): MessageDTO
}