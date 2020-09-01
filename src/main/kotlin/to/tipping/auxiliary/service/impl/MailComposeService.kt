package to.tipping.auxiliary.service.impl

import org.springframework.stereotype.Service
import to.tipping.auxiliary.model.MailMessageDTO
import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.utils.Type

@Service
class MailComposeService : AbstractComposeService() {

    override fun create(newsDTO: NewsDTO): MessageDTO {
        val messageDTO = MailMessageDTO()
        messageDTO.body.title = newsDTO.subject
        messageDTO.body.content = newsDTO.content

//        messageDTO.getBody().setTemplateId(newsDTO.getTemplateId());
//
//        NotificationTemplates templates = getTemplate(newsDTO.getTemplateId());
//        if(templates == null|| !Type.MAIL.getValue().equals(templates.getType()))
//            throw new IllegalArgumentException("模版无效 key " + newsDTO.getTemplateId());
//
//        messageDTO.getBody().setTitle(templates.getSubject());
//        messageDTO.getBody().setContent(getEmailContent(templates, newsDTO));
        return messageDTO
    } //    private String getEmailContent(NotificationTemplates templates,NewsDTO newsDTO){

    override fun type(): Type {
        return Type.MAIL
    }
    //        String content = templates.getContent();
    //        if(newsDTO.getParams() != null){
    //            for (String key:newsDTO.getParams().keySet()){
    //                content = content.replace("{"+key+"}", newsDTO.getParams().get(key));
    //            }
    //        }
    //        return content;
    //    }
}