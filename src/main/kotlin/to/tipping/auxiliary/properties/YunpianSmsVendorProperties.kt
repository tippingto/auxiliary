package to.tipping.auxiliary.properties

import org.springframework.context.annotation.Configuration

@Configuration
data class YunpianSmsVendorProperties(
    //    @Value("${ntf.sms.yunpian.apikey}")
    val apiKey: String = ""
)