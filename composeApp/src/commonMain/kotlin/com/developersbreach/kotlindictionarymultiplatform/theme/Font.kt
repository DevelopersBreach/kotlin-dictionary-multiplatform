package com.developersbreach.kotlindictionarymultiplatform.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlindictionarymultiplatform.composeapp.generated.resources.Res
import kotlindictionarymultiplatform.composeapp.generated.resources.noto_sans_bold
import kotlindictionarymultiplatform.composeapp.generated.resources.noto_sans_medium
import kotlindictionarymultiplatform.composeapp.generated.resources.noto_sans_regular
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font

@OptIn(ExperimentalResourceApi::class)
@Composable
fun AppTypography(): Typography {
    val fontFamily = FontFamily(
        Font(Res.font.noto_sans_regular, weight = FontWeight.Normal),
        Font(Res.font.noto_sans_medium, weight = FontWeight.Medium),
        Font(Res.font.noto_sans_bold, weight = FontWeight.Bold),
    )

    return Typography(
        h1 = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 30.sp),
        h2 = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp),
        h3 = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 24.sp),
        h4 = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 18.sp),
        body1 = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp),
        body2 = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp),
        subtitle1 = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp),
        subtitle2 = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 14.sp),
        button = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp),
        caption = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp),
        overline = TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 10.sp),
    )
}