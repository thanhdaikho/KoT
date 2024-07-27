package com.poly.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.poly.assignment.ui.theme.DomineFont
import com.poly.assignment.ui.theme.LoraFont

@Composable
fun LoginScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val auth = FirebaseAuth.getInstance()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(start = 24.dp, end = 24.dp, top = 56.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Divider(modifier = Modifier
                .weight(0.3f)
                .height(1.dp)
                .width(13.dp), color = Color(0xFFBDBDBD))
            Image(painter = painterResource(id = R.drawable.header_icon), contentDescription = "",
                modifier = Modifier
                    .padding(start = 36.dp, end = 36.dp)
                    .graphicsLayer(2.6f, 2.6f))
            Divider(modifier = Modifier
                .weight(0.3f)
                .height(1.dp)
                .width(13.dp), color = Color(0xFFBDBDBD))
        }
        Row(modifier = Modifier.fillMaxWidth().padding(top = 86.dp)) {
            Text(text = buildAnnotatedString {
                withStyle(ParagraphStyle(lineHeight = 49.sp)) {
                    withStyle(SpanStyle(
                        fontFamily = DomineFont,
                        fontSize = 35.sp,
                        color = Color(0xFF909090)
                    )
                    ) {
                        append("Hello !\n")
                    }
                    withStyle(SpanStyle(
                        fontFamily = DomineFont,
                        fontSize = 31.sp,
                        color = Color.Black
                    )) {
                        append("WELCOME BACK")
                    }
                }
            })
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(610.dp)
            .padding(top = 130.dp)
            .align(Alignment.Center)
            .shadow(elevation = 5.dp, shape = RoundedCornerShape(6.dp))
            .padding(top = 50.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)) {
            Text(text = "Email", color = Color(0xFF909090))
            TextField(
                value = email,
                onValueChange = { email = it },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFE0E0E0),
                    unfocusedIndicatorColor = Color(0xFFE0E0E0)
                ),
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Text(text = "Password", color = Color(0xFF909090), modifier = Modifier.padding(top = 80.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFE0E0E0),
                    unfocusedIndicatorColor = Color(0xFFE0E0E0)
                ),
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val img = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(imageVector = img, contentDescription = "")
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 100.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Text(
                text = "Forgot Password",
                textAlign = TextAlign.Center,
                fontFamily = LoraFont,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(top = 38.dp)
                    .clickable { }
            )
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 160.dp).align(Alignment.Center)
                )
            }
            Button(
                onClick = {
                    auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate("home") {
                                    popUpTo("login") { inclusive = true }
                                }
                            } else {
                                errorMessage = task.exception?.message
                            }
                        }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 76.dp)
                    .align(Alignment.BottomCenter)
                    .height(50.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .shadow(elevation = 1.dp, shape = RoundedCornerShape(6.dp))
            ) {
                Text(text = "Log In", color = Color.White, fontSize = 19.sp)
            }
            Text(text = "SIGN UP",
                fontFamily = DomineFont,
                fontWeight = FontWeight.W300,
                fontSize = 19.sp,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 15.dp)
                    .clickable { navController.navigate("signup") })
        }
    }
}
