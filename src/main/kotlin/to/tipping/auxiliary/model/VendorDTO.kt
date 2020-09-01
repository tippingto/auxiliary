package to.tipping.auxiliary.model

import to.tipping.auxiliary.utils.Vendor

/**
 * @author william
 */
interface VendorDTO {
    val vendor: Vendor
    val tos: Array<String>
}