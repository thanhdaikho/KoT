package com.poly.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun SignupScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var cfPasswordVisible by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize().padding(start = 24.dp, end = 24.dp, top = 56.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Divider(modifier = Modifier.weight(0.3f).height(1.dp).width(13.dp), color = Color(0xFFBDBDBD))
            Image(
                painter = painterResource(id = R.drawable.header_icon),
                contentDescription = "",
                modifier = Modifier.padding(start = 36.dp, end = 36.dp).graphicsLayer(2.6f, 2.6f)
            )
            Divider(modifier = Modifier.weight(0.3f).height(1.dp).width(13.dp), color = Color(0xFFBDBDBD))
        }
        Text(
            text = "WELCOME",
            fontSize = 28.sp,
            fontFamily = DomineFont,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 80.dp)
        )
        Box(
            modifier = Modifier.fillMaxWidth().height(712.dp).padding(top = 90.dp).align(Alignment.Center)
                .shadow(elevation = 5.dp, shape = RoundedCornerShape(6.dp)).padding(top = 50.dp, bottom = 24.dp, start = 24.dp, end = 24.dp)
        ) {
            Text(text = "Name", color = Color(0xFF909090))
            TextField(
                value = name,
                onValueChange = { name = it },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFE0E0E0),
                    unfocusedIndicatorColor = Color(0xFFE0E0E0)
                ),
                modifier = Modifier.fillMaxWidth().padding(top = 15.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Text(text = "Email", color = Color(0xFF909090), modifier = Modifier.padding(top = 100.dp))
            TextField(
                value = email,
                onValueChange = { email = it },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFE0E0E0),
                    unfocusedIndicatorColor = Color(0xFFE0E0E0)
                ),
                modifier = Modifier.fillMaxWidth().padding(top = 117.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Text(text = "Password", color = Color(0xFF909090), modifier = Modifier.padding(top = 200.dp))
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
                        Icon(imageVector = img, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 220.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
            )
            Text(
                text = "Confirm Password",
                color = Color(0xFF909090),
                modifier = Modifier.padding(top = 307.dp)
            )
            TextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color(0xFFE0E0E0),
                    unfocusedIndicatorColor = Color(0xFFE0E0E0)
                ),
                visualTransformation = if (cfPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val img = if (cfPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(onClick = { cfPasswordVisible = !cfPasswordVisible }) {
                        Icon(imageVector = img, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth().padding(top = 327.dp),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )
            Button(
                onClick = {
                    signUpWithEmail(email, password, navController)
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF242424)),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth().padding(bottom = 68.dp).align(Alignment.BottomCenter)
                    .height(50.dp).clip(shape = RoundedCornerShape(16.dp)).shadow(elevation = 1.dp, shape = RoundedCornerShape(6.dp))
            ) {
                Text(text = "SIGN UP", color = Color.White, fontSize = 19.sp)
            }
            Row(modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 10.dp)) {
                Text(text = "Already have an account? ", color = Color.Gray)
                Text(
                    text = "SIGN IN",
                    fontWeight = FontWeight.W500,
                    modifier = Modifier.clickable { navController.navigate("login") }
                )
            }
        }
    }
}

fun signUpWithEmail(email: String, password: String, navController: NavController) {
    val auth = FirebaseAuth.getInstance()

    if (email.isNotEmpty() && password.isNotEmpty()) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    navController.navigate("home") {
                        popUpTo("signup") { inclusive = true }
                    }
                } else {
                    // Handle the error
                    task.exception?.message?.let {
                        // Show error message to the user
                    }
                }
            }
    } else {
        // Show error message that fields are empty
    }
}

