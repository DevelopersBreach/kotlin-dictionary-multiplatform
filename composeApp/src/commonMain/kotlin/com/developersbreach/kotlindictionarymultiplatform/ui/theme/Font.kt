package com.developersbreach.kotlindictionarymultiplatform.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.noto_sans_bold
import kotlindictionarymultiplatform.composeapp.generated.resources.noto_sans_medium
import kotlindictionarymultiplatform.composeapp.generated.resources.noto_sans_regular
import org.jetbrains.compose.resources.Font

@Composable
fun AppTypography(): Typography {
    val fontFamily = FontFamily(
        Font(Res.font.noto_sans_regular, weight = FontWeight.Normal),
        Font(Res.font.noto_sans_medium, weight = FontWeight.Medium),
        Font(Res.font.noto_sans_bold, weight = FontWeight.Bold),
    )

    return Typography(
        displayLarge = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 30.sp),
        displayMedium = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 22.sp),
        displaySmall = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 24.sp),
        headlineLarge = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 18.sp, lineHeight = (18 * 1.3).sp, letterSpacing = (18 * 0.012).sp),
        headlineMedium = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 18.sp, lineHeight = (18 * 1.3).sp, letterSpacing = (18 * 0.012).sp),
        bodyLarge = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
        bodyMedium = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp),
        titleLarge = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 18.sp),
        titleMedium = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp),
        titleSmall = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp),
        labelLarge = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp),
        labelMedium = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp, lineHeight = (18 * 1.3).sp, letterSpacing = (18 * 0.012).sp),
        labelSmall = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 10.sp),
    )
}