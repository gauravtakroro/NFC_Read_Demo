package io.gauravtak.nfc_read_demo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import io.gauravtak.nfc_read_demo.databinding.NfcScanBottomSheetBinding

class NFCScanBottomSheetView @JvmOverloads constructor(
    context: Context,
    attrSet: AttributeSet? = null,
    defStyleResource: Int = 0
) : FrameLayout(context, attrSet, defStyleResource) {
    var bindingNFCScanBottomSheetView: NfcScanBottomSheetBinding
    init {
        bindingNFCScanBottomSheetView = NfcScanBottomSheetBinding.inflate(LayoutInflater.from(context), this, true)
    }
}
