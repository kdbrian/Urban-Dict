package io.kdbrian.urbandict.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import io.kdbrian.urbandict.R


val telma by lazy {
    FontFamily(
        Font(resId = R.font.telmabold, style = FontStyle.Normal),
        Font(resId = R.font.telmablack, style = FontStyle.Normal),
        Font(resId = R.font.telmalight, style = FontStyle.Normal),
        Font(resId = R.font.telmamedium, style = FontStyle.Normal),
        Font(resId = R.font.telmaregular, style = FontStyle.Normal),
    )
}

val gambarino by lazy {
    FontFamily(Font(resId = R.font.gambarinoregular, style = FontStyle.Normal))
}

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)