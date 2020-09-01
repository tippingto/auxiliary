package to.tipping.auxiliary.properties

import org.springframework.context.annotation.Configuration

@Configuration
data class AliEmailVendorProperties(
    //    @Value("${ntf.email.ali.smtp}")
    val smtp: String = "",

    //    @Value("${ntf.email.ali.from}")
    val from: String = "",

    //    @Value("${ntf.email.ali.from-name}")
    val fromName: String = "",

    //    @Value("${ntf.email.ali.use-ssl}")
    val useSsl: Boolean = false,

    //    @Value("${ntf.email.ali.port}")
    val port: Int = 0,

    //    @Value("${ntf.email.ali.password}")
    val password: String = ""
)