package io.gauravtak.nfc_read_demo

import android.nfc.Tag
import android.nfc.tech.Ndef
import android.util.Log
import io.gauravtak.nfc_read_demo.ConstantsValues.NFC_PATH

private const val TAG_ID_LENGTH = 6
private const val TAG_ID_STARTING_CHAR = '2'

fun onNFCTagDetected(
    tag: Tag,
    onProcessStart: () -> Unit,
    onTagDetected: (tagId: String) -> Unit,
    onInvalidTagDetected: () -> Unit
) {
    onProcessStart()
    tag.getPayload()?.let { payload ->
        Log.d("payload:"," -${payload}-")
        val startIndex = payload.indexOf(TAG_ID_STARTING_CHAR)
        if (startIndex >= 0) {
            val tagId = payload.substring(startIndex).trim()
            if (tagId.length == TAG_ID_LENGTH) {
                Log.d("payload tagId:"," -${tagId}-")
                onTagDetected(tagId)
                return
            }
        }
    }
    onInvalidTagDetected()
}
fun Tag.getPayload(): String? {
    if (techList.contains(NFC_PATH)) {
        val ndefTag = Ndef.get(this)
        val ndefMessage = ndefTag.cachedNdefMessage
        if (ndefMessage.records.isNotEmpty()) {
            return String(ndefMessage.records[0].payload)
        }
    }
    return null
}