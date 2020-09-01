package to.tipping.auxiliary.model

/**
 * @author william
 */
data class GenericResponse<T>(

    var success: Boolean = false
) {
    var error: ErrorDTO? = null
    var data: T? = null
    var page: PageDTO? = null
}