package com.poly.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.poly.assignment.ui.theme.DomineFont
import com.poly.assignment.ui.theme.GelasioFont

@Composable
fun ProfileScreen(navController: NavController) {
    Column(modifier = Modifier.padding(top = 30.dp)) {
        Row {
            Image(painter = painterResource(id = R.drawable.seach_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(27.dp)
                    .align(Alignment.CenterVertically)
                    .alpha(0.6f)
                    .clickable {})
            Spacer(modifier = Modifier.weight(1f))
            Column(
                modifier = Modifier.weight(8f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Make home",
                    color = Color(0xFF909090),
                    fontSize = 21.sp,
                    fontFamily = DomineFont,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.logout),
                contentDescription = "",
                modifier = Modifier
                    .size(27.dp)
                    .align(Alignment.CenterVertically)
                    .alpha(0.6f)
                    .clickable {
                        FirebaseAuth.getInstance().signOut()
                        navController.navigate("welcome") {
                            popUpTo("home") { inclusive = true }
                        }
                    })
        }
    }
}