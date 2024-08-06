import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.poly.assignment.Product
import com.poly.assignment.ProductViewModel
import com.poly.assignment.ui.theme.GelasioFont
import com.poly.assignment.ui.theme.NunitoFont

@Composable
fun ProductScreen(product: Product, viewModel: ProductViewModel = viewModel()) {
    var selectedColorIndex by remember { mutableStateOf(0) }
    var quantityCountState by remember { mutableStateOf(1) }
    val colors = listOf(Color.White, Color(0xFFB4916C), Color(0xFFE4CBAD))
    val savedProducts by viewModel.savedProducts.collectAsState()
    val isSaved = savedProducts.contains(product)


    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Display Image
        if (product.images.isNotEmpty()) {
            val imageUrl = product.images.getOrElse(selectedColorIndex) { product.images.first() }
            val processedImageUrl =
                imageUrl.replace("http://localhost:4000", "http://192.168.1.5:4000")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(470.dp)
            ) {
                Image(
                    painter = rememberImagePainter(processedImageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .width(355.dp)
                        .fillMaxHeight() // Make Image fill the Box
                        .clip(RoundedCornerShape(bottomStart = 40.dp)),
                    contentScale = ContentScale.Crop // Adjust scaling as needed
                )
                Box(modifier = Modifier
                    .padding(start = 32.dp, top = 47.dp)
                    .size(50.dp)
                    .clip(shape = RoundedCornerShape(23))
                    .background(color = Color.White)
                    .clickable { }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(200.dp)
                        .padding(start = 30.dp)
                        .padding(end = 14.dp, top = 14.dp)
                        .clip(shape = RoundedCornerShape(40.dp))
                        .align(Alignment.CenterStart)
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center, // Center items in the column
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxSize()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(17.dp), // Space between items
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            colors.forEachIndexed { index, color ->
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(shape = RoundedCornerShape(100))
                                        .background(color)
                                        .clickable { selectedColorIndex = index }
                                        .border(
                                            6.dp,
                                            if (index == selectedColorIndex) Color(0xFF909090) else Color(
                                                0xFFF0F0F0
                                            ),
                                            shape = RoundedCornerShape(100)
                                        )
                                )
                            }
                        }
                    }
                }

            }
        } else {
            // Fallback if no images are available
            Text("No images available")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .padding(start = 25.dp, top = 10.dp, end = 25.dp)
        ) {
            Column {
                Text(
                    text = product.name,
                    fontFamily = GelasioFont,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp,
                    color = Color(0xFF303030)
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween, // Ensure content is spread evenly
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$ ${product.new_price}",
                        fontFamily = NunitoFont,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.Red
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(30.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFE0E0E0))
                                .clickable {
                                    quantityCountState = maxOf(1, quantityCountState - 1)
                                    Log.d("ProductScreen", "Quantity: $quantityCountState")
                                } // Prevent quantity from going below 1
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
                            text = quantityCountState.toString(),
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
                                .clickable { quantityCountState += 1
                                    Log.d("ProductScreen", "Quantity: $quantityCountState") }
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
                Row(
                    modifier = Modifier
                        .padding(vertical = 9.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "",
                        tint = Color(0xFFF2C94C),
                        modifier = Modifier.size(31.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "4.5",
                        color = Color(0xFF303030),
                        fontFamily = NunitoFont,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        modifier = Modifier
                            .padding(top = 5.dp)
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    Text(
                        text = "(50 reviews)",
                        color = Color(0xFF808080),
                        fontFamily = NunitoFont,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center,
                        fontSize = 17.sp,
                        modifier = Modifier
                            .padding(top = 6.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = product.description,
                    fontFamily = NunitoFont,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Light,
                    color = Color(0xFF606060),
                    maxLines = 6,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.padding(top = 15.dp)) {
                    Box(modifier = Modifier
                        .size(60.dp)
                        .clip(shape = RoundedCornerShape(14.dp))
                        .background(color = Color(0xFFF0F0F0))
                        .clickable {
                            viewModel.toggleSaveProduct(product)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.BookmarkBorder, contentDescription = "",
                            Modifier
                                .size(26.dp)
                                .align(Alignment.Center)
                        )
                    }
                    Spacer(modifier = Modifier.width(20.dp))
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(shape = RoundedCornerShape(14.dp))
                        .background(color = Color.Black)
                        .clickable {
                            val productItem =
                                ProductItem(product = product, quantity = quantityCountState)
                            viewModel.toggleCartProduct(productItem)
                        }) {
                        Text(
                            text = "Add to cart",
                            fontFamily = NunitoFont,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 20.sp, color = Color.White,
                            modifier = Modifier
                                .align(Alignment.Center)
                        )
                    }
                }
            }
        }


    }
}

data class ProductItem(
    val product: Product,
    var quantity: Int = 1
)
