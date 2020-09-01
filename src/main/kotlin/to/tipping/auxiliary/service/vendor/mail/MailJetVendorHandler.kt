package to.tipping.auxiliary.service.vendor.mail

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import to.tipping.auxiliary.model.MailJetVendorDTO
import to.tipping.auxiliary.model.MessageDTO
import to.tipping.auxiliary.model.ResultDTO
import to.tipping.auxiliary.model.VendorDTO
import to.tipping.auxiliary.utils.State
import javax.annotation.Resource

@Component
class MailJetVendorHandler(

    @Resource
    val emailSender: JavaMailSender

) : MailVendorHandler() {

    override fun assemble(messageDTO: MessageDTO): VendorDTO {
        return MailJetVendorDTO(messageDTO.router.tos.split("\\|").toTypedArray())
    }

    override fun send(vendorDTO: VendorDTO, messageDTO: MessageDTO): ResultDTO {
        val resultDTO = composeResultDTO(vendorDTO, messageDTO)

        val isOk = sendMail(
            vendorDTO.tos, messageDTO.body.title, messageDTO.body.content
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
        subject: String?,
        context: String
    ): Boolean {
        try {
            val message = SimpleMailMessage()
            message.setFrom("no-reply@tipping.to")
            message.setTo("w@tipping.to")
            message.setSubject(subject!!)
            message.setText(context)
            emailSender.send(message)
            return true
        } catch (e: Exception) {
            log.info("failed: {}", e.message)
        }
        return false
    }

    override fun isOnline(): Boolean {
        return true
    }
}