package com.poly.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.poly.assignment.ui.theme.MerriweatherFont

@Composable
fun ResponseScreen(navController: NavController) {
    Column (modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)
        .padding(top = 63.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SUCCESS !",
            color = Color(0xFF303030),
            fontFamily = MerriweatherFont,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(top = 30.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(400.dp),
            contentAlignment = Alignment.Center
        ){
            Image(painter = painterResource(id = R.drawable.container), contentDescription = "",
                Modifier.size(370.dp))
            Image(painter = painterResource(id = R.drawable.main), contentDescription = "", Modifier.size(240.dp))
            Image(painter = painterResource(id = R.drawable.check_icon), contentDescription = "",
                Modifier
                    .size(80.dp)
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 17.dp))
        }
        Text(
            text = "Your order will be delivered soon.\n" +
                    "Thank you for choosing our app!", color = Color(0xFF606060), textAlign = TextAlign.Center, fontSize = 20.sp,
            modifier = Modifier
                .padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(50.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xFF242424))
            .clickable {
            },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Track your orders", color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
        }
        Spacer(modifier = Modifier.height(25.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, color = Color(0xFF303030), shape = RoundedCornerShape(10.dp))
            .height(60.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color.Transparent)
            .clickable {
                navController.navigate("home")
            },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "BACK TO HOME", color = Color(0xFF303030), fontSize = 24.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
        }
    }
}