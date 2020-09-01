package to.tipping.auxiliary.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author william
 */
@Configuration
@ConfigurationProperties("email.mailjet")
class MailjetEmailProperties {

    lateinit var method: String
    lateinit var smtp: String
    var port = 0
    lateinit var username: String
    lateinit var password: String
    var smtpAuth = false
    var tlsEnable = false
    var sslEnable = false
    lateinit var from: String
    lateinit var fromName: String
    var batchSize = 20
}