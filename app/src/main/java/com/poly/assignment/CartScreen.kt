package com.poly.assignment

import ProductItem
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.compose.material.icons.outlined.ArrowForwardIos
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.poly.assignment.ui.theme.GelasioFont
import com.poly.assignment.ui.theme.NunitoFont

@Composable
fun CartScreen(navController: NavController, viewModel: ProductViewModel = viewModel()) {
    val cartProducts by viewModel.cartProducts.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()
    var promoCodeState by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 42.dp, start = 16.dp, end = 16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier
                        .size(27.dp)
                        .alpha(0.6f)
                        .clickable {}
                )
                Spacer(modifier = Modifier.weight(1f))
                Column(
                    modifier = Modifier.weight(8f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "My Cart",
                        color = Color.Black,
                        fontWeight = FontWeight.W600,
                        fontSize = 22.sp,
                        letterSpacing = 1.sp,
                        fontFamily = GelasioFont,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 3.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            LazyColumn(modifier = Modifier.padding(top = 50.dp, bottom = 20.dp)) {
                itemsIndexed(cartProducts.toList()) { index, productItem ->
                    ProductItems(
                        productItem = productItem,
                        viewModel = viewModel,
                        isLastItem = index == cartProducts.size - 1
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomEnd)
                    .padding(start = 8.dp, end = 10.dp, top = 10.dp, bottom = 50.dp)
            ) {
                Row(modifier = Modifier.padding(bottom = 15.dp)) {
                    Box {
                        TextField(
                            value = promoCodeState,
                            onValueChange = { promoCodeState = it },
                            colors = TextFieldDefaults.colors(
                                unfocusedContainerColor = Color(0xFFFFFFFF),
                                focusedContainerColor = Color(0xFFFFFFFF),
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            modifier = Modifier
                                .width(305.dp)
                                .clip(shape = RoundedCornerShape(16.dp)),
                            placeholder = { Text("Enter Your Promo Code") },
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .clip(shape = RoundedCornerShape(16.dp))
                            .background(color = Color(0xFF303030))
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ArrowForwardIos,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
                Row {
                    Text(
                        text = "Total:",
                        color = Color(0xFF808080),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = "$${totalPrice}",
                        color = Color(0xFF303030),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 18.dp)
                        .height(60.dp)
                        .clip(shape = RoundedCornerShape(16.dp))
                        .background(color = Color(0xFF242424))
                        .clickable { navController.navigate("checkout") },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Check out",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 21.sp,
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            }
        }
    }
}



@Composable
fun ProductItems(
    productItem: ProductItem,
    viewModel: ProductViewModel = viewModel(),
    isLastItem: Boolean
) {
    var quantity by remember { mutableStateOf(productItem.quantity) }

    val imageUrl = productItem.product.images.firstOrNull() ?: ""
    val processedImageUrl =
        imageUrl.replace("http://localhost:4000", "http://192.168.1.5:4000")
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = processedImageUrl),
                contentDescription = null,
                modifier = Modifier
                    .size(130.dp)
                    .padding(end = 25.dp)
            )
            Column {
                Row(modifier = Modifier.padding(top = 12.dp)) {
                    Text(
                        text = productItem.product.name,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        fontFamily = NunitoFont,
                        color = Color(0xFF606060)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    IconButton(
                        onClick = { viewModel.toggleCartProduct(productItem) },
                        modifier = Modifier
                            .size(35.dp)
                            .align(Alignment.CenterVertically)
                    ) {
                        Icon(imageVector = Icons.Outlined.Cancel, contentDescription = "")
                    }
                }

                Text(
                    text = "$${productItem.product.new_price}",
                    color = Color(0xFF303030),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 12.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFE0E0E0))
                            .clickable {
                                if (quantity > 1) {
                                    quantity -= 1
                                    viewModel.updateProductQuantity(productItem, quantity)
                                }
                            }
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Remove,
                            contentDescription = "Decrease quantity",
                            tint = Color.Black,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = quantity.toString(),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Box(
                        modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFFE0E0E0))
                            .clickable {
                                quantity += 1
                                viewModel.updateProductQuantity(productItem, quantity)
                            }
                            .padding(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Increase quantity",
                            tint = Color.Black,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
        if (!isLastItem) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color(0xFFF0F0F0))
            )
        }
    }
}

