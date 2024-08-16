package com.poly.assignment.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.poly.assignment.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val welcomeFont = GoogleFont("DM Serif Display")
val welcomeFontFamily = FontFamily(
    Font(
        googleFont = welcomeFont,
        fontProvider = provider
    )
)

val Manrope = GoogleFont("Manrope")
val ManropeFont = FontFamily(
    Font(
        googleFont = Manrope,
        fontProvider = provider
    )
)
val Merriweather = GoogleFont("Merriweather")
val MerriweatherFont = FontFamily(
    Font(
        googleFont = Merriweather,
        fontProvider = provider
    )
)
val Lora = GoogleFont("Lora")
val LoraFont = FontFamily(
    Font(
        googleFont = Lora,
        fontProvider = provider
    )
)

val Domine = GoogleFont("Domine")
val DomineFont = FontFamily(
    Font(
        googleFont = Domine,
        fontProvider = provider
    )
)


val Gelasio = GoogleFont("Gelasio")
val GelasioFont = FontFamily(
    Font(
        googleFont = Gelasio,
        fontProvider = provider
    )
)

val Nunito = GoogleFont("Nunito Sans")
val NunitoFont = FontFamily(
    Font(
        googleFont = Nunito,
        fontProvider = provider
    )
)
// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = NunitoFont,
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