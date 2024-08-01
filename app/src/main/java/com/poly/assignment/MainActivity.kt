package com.poly.assignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.poly.assignment.ui.theme.AssignmentTheme

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = FirebaseAuth.getInstance()

        setContent {
            AssignmentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val userState = remember { mutableStateOf(auth.currentUser) }
                    DisposableEffect(Unit) {
                        val authStateListener = FirebaseAuth.AuthStateListener { auth ->
                            userState.value = auth.currentUser
                        }
                        auth.addAuthStateListener(authStateListener)
                        onDispose {
                            auth.removeAuthStateListener(authStateListener)
                        }
                    }
                    MainScreen(userState.value)
                }
            }
        }
    }
}
@Composable
fun SignOut() {
    //    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(text = "Welcome to the Home Screen!")
//        Spacer(modifier = Modifier.height(16.dp))
//        Button(onClick = {
//            FirebaseAuth.getInstance().signOut()
//            navController.navigate("welcome") {
//                popUpTo("home") { inclusive = true }
//            }
//        }) {
//            Text("Sign Out")
//        }
//    }
}