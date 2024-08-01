package com.poly.assignment

import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.poly.assignment.ui.theme.NunitoFont
import kotlinx.coroutines.launch

@Composable
fun HomeMainSection(route: Int, navController: NavController) {
    val scope = rememberCoroutineScope()
    var products by remember { mutableStateOf<List<Product>?>(null) }

    // Call API based on route
    LaunchedEffect(route) {
        scope.launch {
            products = fetchProductsForRoute(route)
        }
    }

    products?.let {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(it) { product ->
                ProductCard(
                    product = product,
                    onClick = {
                        navController.navigate("product/${product.id}")
                    }
                )
            }
        }
    } ?: run {
        Text("Loading...")
    }
}

suspend fun fetchProductsForRoute(route: Int): List<Product> {
    val url = when (route) {
        0 -> "http://192.168.1.5:4000/allproducts"
        1 -> "http://192.168.1.5:4000/hotchair"
        2 -> "http://192.168.1.5:4000/hottable"
        3 -> "http://192.168.1.5:4000/hotarmchair"
        4 -> "http://192.168.1.5:4000/hotbed"
        5 -> "http://192.168.1.5:4000/hotlamp"
        else -> "http://192.168.1.5:4000/allproducts"
    }
    return ProductRepository.fetchProducts(url).map { product ->
        return ProductRepository.fetchProducts(url)
    }
}

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onClick(product) },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(

        ) {
            if (product.images.isNotEmpty()) {
                Image(
                    painter = rememberImagePainter(product.images.first()),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(210.dp)
                        .clip(RoundedCornerShape(8.dp)),

                    contentScale = ContentScale.FillHeight
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = product.name, fontFamily = NunitoFont, color = Color(0xFF606060), modifier = Modifier.padding(horizontal = 4.dp))
            Row (modifier = Modifier.fillMaxWidth()){
                Text(text = "$ ${product.old_price} ", color = Color.Black, textDecoration = TextDecoration.LineThrough, modifier = Modifier.padding(horizontal = 4.dp))
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "$ ${product.new_price} ", fontWeight = FontWeight.Bold, color = Color.Red, textAlign = TextAlign.End, modifier = Modifier.padding(horizontal = 4.dp))
            }
        }
    }
}


data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val images: List<String>,
    val category: String,
    val new_price: Double,
    val old_price: Double,
    val available: Boolean
)
