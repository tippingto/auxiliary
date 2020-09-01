package to.tipping.auxiliary.service.vendor.mail

import org.springframework.mail.MailAuthenticationException
import org.springframework.mail.MailParseException
import org.springframework.mail.MailSendException
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import to.tipping.auxiliary.model.MailJetVendorDTO
import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.model.VendorDTO
import to.tipping.auxiliary.properties.MailjetEmailProperties
import to.tipping.auxiliary.utils.State
import javax.annotation.Resource

@Component
class MailJetVendorHandler(

    @Resource
    val emailSender: JavaMailSender,

    @Resource
    val mailjetEmailProperties: MailjetEmailProperties

) : MailVendorHandler() {

    override fun assemble(messageDTO: MessageDTO): VendorDTO {
        return MailJetVendorDTO(messageDTO.router.tos.split("|").toTypedArray())
    }

    override fun send(vendorDTO: VendorDTO, messageDTO: MessageDTO): Array<ResultDTO> {

        val results = Array(vendorDTO.tos.size) { composeResultDTO(vendorDTO, messageDTO) }

        messageDTO.body.title?.let {
            sendMail(
                vendorDTO.tos, it, messageDTO.body.content, results
            )
        }
        
        return results
    }

    private fun sendMail(
        tos: Array<String>,
        subject: String,
        context: String,
        results: Array<ResultDTO>
    ) {

        val messages = Array(tos.size) { SimpleMailMessage() }

        val src = SimpleMailMessage()
        src.setFrom(mailjetEmailProperties.from)
        src.setSubject(subject)
        src.setText(context)

        for (i in tos.indices) {
            src.copyTo(messages[i])
            messages[i].setTo(tos[i])
        }

        try {
            emailSender.send(*messages)
        } catch (e: Exception) {
            when (e) {
                is MailParseException,
                is MailAuthenticationException,
                is MailSendException -> {
                    for (result in results) {
                        result.success = false
                        result.state = State.ERROR
                        result.reason = e.localizedMessage
                    }
                }
                else -> throw e
            }
        }
    }

    override fun isOnline(): Boolean {
        return true
    }
}