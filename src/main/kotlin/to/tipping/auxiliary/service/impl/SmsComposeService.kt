package to.tipping.auxiliary.service.impl

import org.springframework.stereotype.Service
import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.NewsDTO
import to.tipping.auxiliary.model.SmsMessageDTO
import to.tipping.auxiliary.utils.Type

/**
 * @author william
 */
@Service
class SmsComposeService : AbstractComposeService() {

    override fun create(newsDTO: NewsDTO): MessageDTO {

//        NotificationTemplates templates = getTemplate(newsDTO.getTemplateId());
//        if(templates == null || !Type.SMS.getValue().equals(templates.getType()))
//            throw new IllegalArgumentException("模版无效 key " + newsDTO.getTemplateId());
//
//        messageDTO.getBody().setTemplateId(templates.getSubject());
//        messageDTO.getRouter().setNativeCode(newsDTO.getNativeCode());
//        messageDTO.getBody().setParams(newsDTO.getParams());
//        messageDTO.getBody().setContent(getSMSContent(templates, newsDTO));
        val messageDTO = SmsMessageDTO()
        messageDTO.body.content = newsDTO.content
        return messageDTO
    } //    private String getSMSContent(NotificationTemplates templates,NewsDTO newsDTO){

    override fun type(): Type {
        return Type.SMS
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