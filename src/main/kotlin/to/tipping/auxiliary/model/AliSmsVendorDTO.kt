package to.tipping.auxiliary.model

import to.tipping.auxiliary.utils.Vendor

/**
 * @author william
 */
class AliSmsVendorDTO(
    override val tos: Array<String>,
    override val vendor: Vendor = Vendor.ALI
) : SmsVendorDTO