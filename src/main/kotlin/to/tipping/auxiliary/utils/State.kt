package to.tipping.auxiliary.utils

/**
 * @author william
 */
enum class State(
    private val _value: Int,
    private val _name: String
) {
    UNKNOWN(0, "unknown"),
    COMPOSE(1, "compose"),
    PREPARE(2, "prepare"),
    SENDING(3, "sending"),
    SENT(4, "sent"),
    FAIL(-1, "fail"),
    ERROR(-2, "error");
}