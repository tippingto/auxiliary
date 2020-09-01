package to.tipping.auxiliary.utils

import java.awt.Color
import java.awt.Font
import java.awt.image.BufferedImage
import java.io.OutputStream
import java.util.*

/**
 * @author william
 */
val mapTable = charArrayOf(
    '0', '1', '2', '3', '4', '5',
    '6', '7', '8', '9', '0', '1',
    '2', '3', '4', '5', '6', '7',
    '8', '9'
)

class CaptchaUtils {

    companion object {

        @JvmStatic
        fun getImageCode(w: Int, h: Int, os: OutputStream?): Map<String, Any>? {

            var width = w
            var height = h
            val returnMap: MutableMap<String, Any> = HashMap()
            if (width <= 0) width = 60
            if (height <= 0) height = 20
            val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val g = image.graphics
            val random = Random()

            g.color = getRandColor(200, 250)
            g.fillRect(0, 0, width, height)
            g.font = Font("Times New Roman", Font.PLAIN, 18)

            g.color = getRandColor(160, 200)
            for (i in 0..167) {
                val x: Int = random.nextInt(width)
                val y: Int = random.nextInt(height)
                val xl: Int = random.nextInt(12)
                val yl: Int = random.nextInt(12)
                g.drawLine(x, y, x + xl, y + yl)
            }

            var strEnsure = ""

            for (i in 0..3) {
                strEnsure += mapTable[(mapTable.size * Math.random()).toInt()]
                g.color = Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110))
                val str = strEnsure.substring(i, i + 1)
                g.drawString(str, 13 * i + 20, 25)
            }
            g.dispose()
            returnMap["image"] = image
            returnMap["strEnsure"] = strEnsure
            return returnMap
        }

        private fun getRandColor(f: Int, b: Int): Color? {
            var fc = f
            var bc = b
            val random = Random()
            if (fc > 255) fc = 255
            if (bc > 255) bc = 255
            val r: Int = fc + random.nextInt(bc - fc)
            val g: Int = fc + random.nextInt(bc - fc)
            val b: Int = fc + random.nextInt(bc - fc)
            return Color(r, g, b)
        }
    }
}