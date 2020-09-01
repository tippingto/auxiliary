package to.tipping.auxiliary.service.vendor.sms

import com.fasterxml.jackson.databind.ObjectMapper
import lombok.SneakyThrows
import org.springframework.stereotype.Component
import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.model.VendorDTO
import to.tipping.auxiliary.model.YunpianSmsVendorDTO
import to.tipping.auxiliary.properties.YunpianSmsVendorProperties
import javax.annotation.Resource

@Component
class YunpianSmsVendorHandler(

    @Resource
    val objectMapper: ObjectMapper,

    @Resource
    val yunpianSmsVendorProperties: YunpianSmsVendorProperties
) : SmsVendorHandler() {

    override fun isDefault(): Boolean {
        return false
    }

    override fun isValidNativeCode(messageDTO: MessageDTO?): Boolean {
        return true
    }

    override fun assemble(messageDTO: MessageDTO): VendorDTO {
        val vendorDTO = YunpianSmsVendorDTO(messageDTO.router.tos.split("\\|").toTypedArray())
//        if ("86" == messageDTO.router.nativeCode) {
//            vendorDTO.phoneNumber = messageDTO.router.tos
//        } else {
//            vendorDTO.phoneNumber = "+" + messageDTO.router.nativeCode + messageDTO.router.tos
//        }
        return vendorDTO
    }

    @SneakyThrows
    override fun send(vendorDTO: VendorDTO, messageDTO: MessageDTO): ResultDTO {

//        YunpianClient clnt = new YunpianClient(yunpianSmsVendorProperties.getApiKey()).init();
//
//        Map<String, String> param = clnt.newParam(2);
//        param.put(YunpianClient.MOBILE, vendorDTO.getPhoneNumber());
//        param.put(YunpianClient.TEXT, messageDTO.getBody().getContent());
//
//        log.info("send yunpian sms request {}",param);
//
//        Result<SmsSingleSend> r = clnt.sms().single_send(param);
//
//        //获取返回结果，返回码:r.getCode(),返回码描述:r.getMsg(),API结果:r.getData(),其他说明:r.getDetail(),调用异常:r.getThrowable()
//        SmsSingleSend smsSingleSend = r.getData();
//        //账户:clnt.user().* 签名:clnt.sign().* 模版:clnt.tpl().* 短信:clnt.sms().* 语音:clnt.voice().* 流量:clnt.flow().* 隐私通话:clnt.call().*
//
//        log.info("send yunpian sms response {}",smsSingleSend);
//
//        if(smsSingleSend == null){
//            resultDTO.setState(State.FAIL);
//            resultDTO.setSuccess(false);
//        }else if(smsSingleSend.getCode().equals(0)){
//            resultDTO.setVendorMsgId(smsSingleSend.getSid().toString());
//            resultDTO.setState(State.SENT);
//            resultDTO.setSuccess(true);
//        }else{
//            resultDTO.setReason(objectMapper.writeValueAsString(smsSingleSend));
//            resultDTO.setState(State.ERROR);
//            resultDTO.setSuccess(false);
//        }
//
//        //最后释放client
//        clnt.close();
        return ResultDTO()
    }
}