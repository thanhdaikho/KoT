package com.poly.assignment

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SavedScreen(navController: NavController, viewModel: ProductViewModel = viewModel()) {
    // Ensure that you have an instance of ProductViewModel
    val savedProducts by viewModel.savedProducts.collectAsState()
    // Log saved products when the composable is recomposed
    LaunchedEffect(savedProducts) {
        Log.d("SavedScreen", "Updated saved products: $savedProducts")
        savedProducts.forEach { product ->
            Log.d("Saved Product", product.toString())
        }
    }

    Column {
        Text(
            text = "Saved Products",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn {
            items(savedProducts.toList()) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Text(text = "$${product.new_price}", color = Color.Red, fontSize = 18.sp)
    }
}
