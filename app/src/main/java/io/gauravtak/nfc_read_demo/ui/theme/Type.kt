package io.gauravtak.nfc_read_demo.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.gauravtak.nfc_read_demo.R

val LatoBold = FontFamily(Font(R.font.lato_bold))
val LatoRegular = FontFamily(Font(R.font.lato_regular))

val Typography = Typography(
    displayMedium = TextStyle(
        fontFamily = LatoBold,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    displayLarge = TextStyle(
        fontFamily = LatoBold,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = LatoRegular,
        fontWeight = FontWeight.Thin,
        fontSize = 16.sp
    )
)