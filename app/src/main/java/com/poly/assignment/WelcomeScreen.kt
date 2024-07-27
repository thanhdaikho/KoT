package com.poly.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.poly.assignment.ui.theme.ManropeFont
import com.poly.assignment.ui.theme.welcomeFontFamily

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.boarding),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(start = 24.dp)
        ) {
            Text(
                    text = buildAnnotatedString {
                        withStyle(
                            style = ParagraphStyle(lineHeight = 44.sp)
                        ) {
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = welcomeFontFamily,
                                    color = Color(0xFF606060),
                                    fontSize = 28.sp,
                                    letterSpacing = 2.1.sp
                                )
                            ) {
                                append("MAKE YOUR\n")
                            }
                            withStyle(
                                style = SpanStyle(
                                    fontFamily = welcomeFontFamily,
                                    fontSize = 34.sp,
                                    letterSpacing = 3.6.sp
                                )
                            ) {
                                append("HOME BEAUTIFUL\n")
                            }
                        }

                    },
                    textAlign = TextAlign.Start,
            )
            Text(
                text = "The best simple place where you discover the most wonderful furnitures and make your home beautiful",
                fontSize = 18.sp,
                color = Color(0xFF808080),
                fontFamily = ManropeFont,
                lineHeight = 38.sp,
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(top = 120.dp, start = 24.dp, end = 24.dp) // Add padding here
                    .align(Alignment.Center)
            )
        }
        Box( modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(bottom = 107.dp)
            .background(color = Color(0xFF242424), shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable {
                navController.navigate("login")
            }
        ) {
            Text(text = "Get Started",
                fontFamily = welcomeFontFamily,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.Center),
                fontSize = 18.sp,
                )
        }
    }
}
