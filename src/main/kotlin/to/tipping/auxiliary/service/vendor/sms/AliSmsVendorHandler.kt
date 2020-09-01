package to.tipping.auxiliary.service.vendor.sms

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.SneakyThrows
import org.springframework.stereotype.Component
import to.tipping.auxiliary.model.AliSmsVendorDTO
import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.model.VendorDTO
import to.tipping.auxiliary.properties.AliSmsVendorProperties
import javax.annotation.Resource

@Component
class AliSmsVendorHandler(

    @Resource
    val objectMapper: ObjectMapper,

    @Resource
    val aliSmsVendorProperties: AliSmsVendorProperties
) : SmsVendorHandler() {

    override fun isDefault(): Boolean {
        return false
    }

    override fun isValidNativeCode(messageDTO: MessageDTO?): Boolean {
        return false
    }

    override fun assemble(messageDTO: MessageDTO): VendorDTO {
        val vendorDTO = AliSmsVendorDTO(messageDTO.router.tos.split("\\|").toTypedArray())
//        vendorDTO.phoneNumber = singleDeviceToken
        return vendorDTO
    }

    @SneakyThrows
    override fun send(vendorDTO: VendorDTO, messageDTO: MessageDTO): Array<ResultDTO> {

//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setProtocol(ProtocolType.HTTPS);
//        request.setDomain(aliSmsVendorProperties.getDomain());
//        request.setVersion(aliSmsVendorProperties.getVersion());
//        request.setAction("SendMessageWithTemplate");
//        String phoneNumbers = vendorDTO.getPhoneNumber();
//        if(!StringUtils.isEmpty(messageDTO.getRouter().getNativeCode()) && !"86".equals(messageDTO.getRouter().getNativeCode())) {
//            phoneNumbers = "+" + messageDTO.getRouter().getNativeCode() + vendorDTO.getPhoneNumber();
//        }else{
//            phoneNumbers = messageDTO.getRouter().getNativeCode() + vendorDTO.getPhoneNumber();
//        }
//        request.putQueryParameter("RegionId",aliSmsVendorProperties.getRegionId());
//        request.putQueryParameter("To", phoneNumbers);
//        request.putQueryParameter("From",aliSmsVendorProperties.getSignName());
//        request.putQueryParameter("TemplateCode",messageDTO.getBody().getTemplateId());
//        if(messageDTO.getBody().getParams() != null && messageDTO.getBody().getParams().size() > 0) {
//            request.putQueryParameter("TemplateParam", objectMapper.writeValueAsString(messageDTO.getBody().getParams()));
//        }
//
//        DefaultProfile profile = DefaultProfile.getProfile(aliSmsVendorProperties.getRegionId(), aliSmsVendorProperties.getKey(), aliSmsVendorProperties.getSecret());
//        IAcsClient client = new DefaultAcsClient(profile);
//        log.info("send sms request {}",request);
//        CommonResponse response = client.getCommonResponse(request);
//        log.info("send sms result {}",response.getData());
//
//        AliSmsSendMessageWithTemplateResult resultData = objectMapper.readValue(response.getData(),new TypeReference<AliSmsSendMessageWithTemplateResult>(){});
//
//        if(resultData == null){
//            resultDTO.setState(State.FAIL);
//            resultDTO.setSuccess(false);
//        }else if(resultData != null && "OK".equals(resultData.getResponseCode() )){
//            resultDTO.setVendorMsgId(resultData.getMessageId());
//            resultDTO.setReason(response.getData());
//            resultDTO.setState(State.SENT);
//            resultDTO.setSuccess(true);
//        }else{
//            resultDTO.setReason(response.getData());
//            resultDTO.setState(State.ERROR);
//            resultDTO.setSuccess(false);
//        }
        return emptyArray()
    }
}