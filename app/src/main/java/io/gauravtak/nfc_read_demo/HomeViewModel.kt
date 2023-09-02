package io.gauravtak.nfc_read_demo

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var isNFCSupported: Boolean = false
    var isNFCEnabled: Boolean = false
    val invalidNFCCardDialogState: SingleLiveEvent<Boolean> = SingleLiveEvent()
    val nfcTagScanned: SingleLiveEvent<String> = SingleLiveEvent()
    // this is used to show the result value and show output what scanned using NFC

    fun onNewTag(tagId: String) {
        Log.i("MainViewModel", "onNewTag: $tagId")
        GlobalScope.launch {
            nfcTagScanned.postValue("Scanned Card Value: $tagId")
        }
    }

    fun showInvalidNFCCardDialog() {
        GlobalScope.launch {
            invalidNFCCardDialogState.postValue(true)
        }
    }

    fun hideInvalidNFCCardDialog() {
        GlobalScope.launch {
            invalidNFCCardDialogState.postValue(false)
        }
    }
}