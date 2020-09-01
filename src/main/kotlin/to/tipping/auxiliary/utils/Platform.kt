package to.tipping.auxiliary.utils

/**
 * @author william
 */
enum class Platform
    (
    private var _value: Int,
    private var _name: String
) {
    UNKNOWN(0, "unknown"),
    IOS(1, "ios"),
    ANDROID(2, "android");

    fun forValue(value: Int): Platform {
        if (this._value.compareTo(value) == 0) {
            return this
        }
        return UNKNOWN
    }

    fun forName(name: String): Platform {
        if (this._name === name) {
            return this
        }
        return UNKNOWN
    }
}