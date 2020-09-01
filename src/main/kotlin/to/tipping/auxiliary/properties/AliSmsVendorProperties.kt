package to.tipping.auxiliary.properties

import org.springframework.context.annotation.Configuration

@Configuration
data class AliSmsVendorProperties(
    //    @Value("${ntf.sms.ali.domain}")
    val domain: String = "",

    //    @Value("${ntf.sms.ali.region-id}")
    val regionId: String = "",

    //    @Value("${ntf.sms.ali.sign-name}")
    val signName: String = "",

    //    @Value("${ntf.sms.ali.version}")
    val version: String = "",

    //    @Value("${ntf.sms.ali.key}")
    val key: String = "",

    //    @Value("${ntf.sms.ali.secret}")
    val secret: String = ""
)