package com.poly.assignment

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.poly.assignment.ui.theme.DomineFont
import com.poly.assignment.ui.theme.GelasioFont

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 42.dp, start = 16.dp, end = 16.dp)
        ) {
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
                        text = "Favourites",
                        color = Color.Black,
                        fontWeight = FontWeight.W600,
                        fontSize = 22.sp,
                        letterSpacing = 1.sp,
                        fontFamily = GelasioFont,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(id = R.drawable.cart_icon),
                    contentDescription = "",
                    modifier = Modifier
                        .size(27.dp)
                        .align(Alignment.CenterVertically)
                        .alpha(0.6f)
                        .clickable { navController.navigate("cart") })
            }
        }

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
