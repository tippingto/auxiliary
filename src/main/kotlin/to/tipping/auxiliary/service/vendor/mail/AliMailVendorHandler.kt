package to.tipping.auxiliary.service.vendor.mail

import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import to.tipping.auxiliary.model.AliEmailVendorDTO
import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.model.VendorDTO
import to.tipping.auxiliary.properties.AliEmailVendorProperties
import to.tipping.auxiliary.utils.State
import java.util.*
import javax.annotation.Resource
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage

@Slf4j
@Component
class AliMailVendorHandler : MailVendorHandler() {

    @Resource
    private val aliEmailVendorProperties: AliEmailVendorProperties? = null

    override fun assemble(messageDTO: MessageDTO): VendorDTO {
        val vendorDTO = AliEmailVendorDTO(messageDTO.router.tos.split(",").toTypedArray())
        return vendorDTO
    }

    override fun send(vendorDTO: VendorDTO, messageDTO: MessageDTO): ResultDTO {
        val resultDTO: ResultDTO = composeResultDTO(vendorDTO, messageDTO)
        val isOk = sendMail(
            vendorDTO.tos, null, null,
            messageDTO.body.title, messageDTO.body.content
        )
        if (isOk) {
            resultDTO.state = State.SENT
            resultDTO.success = true
        } else {
            resultDTO.state = State.ERROR
            resultDTO.success = false
        }
        return resultDTO
    }

    private fun sendMail(
        tos: Array<String>,
        ccUser: String?,
        bccUser: String?,
        subject: String?,
        context: String
    ): Boolean {
        // 配置发送邮件的环境属性
        val props = Properties()
        // 表示SMTP发送邮件，需要进行身份验证
        props["mail.smtp.auth"] = "true"
        props["mail.smtp.host"] = aliEmailVendorProperties!!.smtp
        props["mail.smtp.port"] = aliEmailVendorProperties.port
        // 如果使用ssl，则去掉使用25端口的配置，进行如下配置
        if (aliEmailVendorProperties.useSsl) {
            props["mail.smtp.socketFactory.class"] = "javax.net.ssl.SSLSocketFactory"
            props["mail.smtp.socketFactory.port"] = aliEmailVendorProperties.port
        }
        // 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
        props["mail.user"] = aliEmailVendorProperties.from
        // 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
        props["mail.password"] = aliEmailVendorProperties.password
        // 构建授权信息，用于进行SMTP进行身份验证
        val authenticator: Authenticator = object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                // 用户名、密码
                val userName = props.getProperty("mail.user")
                val password = props.getProperty("mail.password")
                return PasswordAuthentication(userName, password)
            }
        }
        // 使用环境属性和授权信息，创建邮件会话
        val mailSession = Session.getInstance(props, authenticator)
        //mailSession.setDebug(true);
        //UUID uuid = UUID.randomUUID();
        //final String messageIDValue = "<" + uuid.toString() + ">";
        // 创建邮件消息
        val message: MimeMessage =
            object : MimeMessage(mailSession) { //@Override
                //protected void updateMessageID() throws MessagingException {
                //设置自定义Message-ID值
                //setHeader("Message-ID", messageIDValue);
                //}
            }
        try {
            // 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
            val from = InternetAddress(
                aliEmailVendorProperties.from,
                aliEmailVendorProperties.fromName
            )
            message.setFrom(from)
            //可选。设置回信地址
            val a = arrayOfNulls<Address>(1)
            a[0] = InternetAddress(aliEmailVendorProperties.from)
            message.replyTo = a
            // 设置收件人邮件地址，比如yyy@yyy.com
            if (tos.size == 1) {
                val to = InternetAddress(tos[0])
                message.setRecipient(MimeMessage.RecipientType.TO, to)
            } else { //如果同时发给多人，才将上面两行替换为如下（因为部分收信系统的一些限制，尽量每次投递给一个人；同时我们限制单次允许发送的人数是30人）：
                val adds =
                    arrayOfNulls<InternetAddress>(tos.size)
                for (i in tos.indices) {
                    adds[i] = InternetAddress(tos[i])
                }
                message.setRecipients(Message.RecipientType.TO, adds)
            }
            // 设置多个抄送地址
            if (!StringUtils.isEmpty(ccUser)) {
//                val internetAddressCC: Array<InternetAddress> =
//                    InternetAddress().parse(ccUser)
//                message.setRecipients(Message.RecipientType.CC, internetAddressCC)
            }
            // 设置多个密送地址
            if (!StringUtils.isEmpty(bccUser)) {
//                val internetAddressBCC: Array<InternetAddress> =
//                    InternetAddress().parse(bccUser)
//                message.setRecipients(Message.RecipientType.BCC, internetAddressBCC)
            }
            // 设置邮件标题
            message.subject = subject
            // 设置邮件的内容体
            message.setContent(context, "text/html;charset=UTF-8")
            //若需要开启邮件跟踪服务，请使用以下代码设置跟踪链接头。首先域名需要备案，设置且已正确解析了CNAME配置；其次发信需要打Tag，此Tag在控制台已创建并存在，Tag创建10分钟后方可使用；
            //String tagName = "Test";
            //HashMap<String, String> trace = new HashMap<>();
            //trace.put("OpenTrace", "1");
            //trace.put("TagName", tagName);
            //String jsonTrace = JSON.toJSONString(trace);
            //String base64Trace = new String(Base64.encodeBase64(jsonTrace.getBytes()));
            //设置跟踪链接头
            //message.addHeader("X-AliDM-Trace", base64Trace);
            // 发送附件，总的邮件大小不超过15M，创建消息部分
            //BodyPart messageBodyPart = new MimeBodyPart();
            // 消息
            //messageBodyPart.setText("消息Text");
            // 创建多重消息
            //Multipart multipart = new MimeMultipart();
            // 设置文本消息部分
            //multipart.addBodyPart(messageBodyPart);
            // 附件部分
            //messageBodyPart = new MimeBodyPart();
            //设置要发送附件的文件路径
            //String filename = "D:\\goProjects\\src\\测试pdf.pdf";
            //FileDataSource source = new FileDataSource(filename);
            //messageBodyPart.setDataHandler(new DataHandler(source));
            //处理附件名称中文（附带文件路径）乱码问题
            //messageBodyPart.setFileName(MimeUtility.encodeText(filename));
            //multipart.addBodyPart(messageBodyPart);
            // 发送含有附件的完整消息
            //message.setContent(multipart);
            // 发送附件代码，结束
            // 发送邮件
            Transport.send(message)
            return true
        } catch (e: Exception) {
            log.info("邮件发送失败", e)
        }
        return false
    }
}