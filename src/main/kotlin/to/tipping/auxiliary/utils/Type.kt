package to.tipping.auxiliary.utils

/**
 * @author william
 */
enum class Type(private val _value: Int, private val _name: String) {

    NONE(0, "none"), SMS(1, "sms"), MAIL(2, "mail"), PUSH(3, "push");

    override fun toString(): String {
        return _name
    }

    companion object {
        fun forValue(value: Int): Type {
            for (type in values()) {
                if (type._value.compareTo(value) == 0) {
                    return type
                }
            }
            return NONE
        }
    }
}