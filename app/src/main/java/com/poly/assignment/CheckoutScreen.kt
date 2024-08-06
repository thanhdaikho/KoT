package com.poly.assignment

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun CheckoutScreen(navController: NavController, viewModel: ProductViewModel = viewModel()) {
    val cartProducts by viewModel.cartProducts.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()

    var address by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("") }
    val user = Firebase.auth.currentUser

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Checkout", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        Text("Address")
        TextField(
            value = address,
            onValueChange = { address = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Payment Method")
        TextField(
            value = paymentMethod,
            onValueChange = { paymentMethod = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Total: $${totalPrice}", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                val userId = user?.uid ?: "unknown"
                // Handle checkout logic here
                viewModel.createBillDetail(userId, address, paymentMethod, cartProducts, totalPrice)
                navController.navigate("bill_detail")
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Confirm & Checkout")
        }
    }
}