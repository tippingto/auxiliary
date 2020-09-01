package to.tipping.auxiliary.utils

import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * @author william
 */
abstract class LogUtils {
    val log: Logger = LoggerFactory.getLogger(this.javaClass)
}