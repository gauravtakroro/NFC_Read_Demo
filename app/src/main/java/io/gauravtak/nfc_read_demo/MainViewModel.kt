package io.gauravtak.nfc_read_demo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var resultValueDisplayed = MutableLiveData("")
    // this is used to show the result value and show output what scanned using NFC

    fun getCurrentNFCReadValue(): MutableLiveData<String> {
        return resultValueDisplayed
    }
}