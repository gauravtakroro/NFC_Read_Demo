package io.gauravtak.nfc_read_demo.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun NFC_Read_DemoTheme(content: @Composable () -> Unit) {

    MaterialTheme(
        typography = Typography, content = content
    )
}