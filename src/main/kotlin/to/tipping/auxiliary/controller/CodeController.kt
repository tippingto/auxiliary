package to.tipping.auxiliary.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import to.tipping.auxiliary.utils.CaptchaUtils
import java.awt.image.BufferedImage
import java.io.IOException
import java.io.OutputStream
import java.util.*
import javax.annotation.Resource
import javax.imageio.ImageIO
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @author william
 */
@Controller
@RequestMapping("/code")
class CodeController {

    @Resource
    lateinit var request: HttpServletRequest

    @Resource
    lateinit var response: HttpServletResponse

    @RequestMapping(value = ["/captcha"])
    @ResponseBody
    @Throws(Exception::class)
    fun captcha(): String? {

        response.setDateHeader("Expires", 0)
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate")
        response.addHeader("Cache-Control", "post-check=0, pre-check=0")
        response.setHeader("Pragma", "no-cache")
        response.contentType = "image/jpeg"

        val os: OutputStream = response.outputStream
        val map: Map<String, Any>? = CaptchaUtils.getImageCode(86, 37, os)
        val simpleCaptcha = "simpleCaptcha"
        request.session?.setAttribute(simpleCaptcha, map?.get("strEnsure").toString().toLowerCase())
        request.session?.setAttribute("codeTime", Date().time)
        try {
            ImageIO.write(map?.get("image") as BufferedImage?, "jpg", os)
        } catch (e: IOException) {
            return ""
        } finally {
            os.flush()
            os.close()
        }
        return null
    }
}