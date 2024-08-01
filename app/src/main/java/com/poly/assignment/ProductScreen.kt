import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.poly.assignment.Product
import com.poly.assignment.ui.theme.NunitoFont

@Composable
fun ProductScreen(product: Product) {
    var selectedColorIndex by remember { mutableStateOf(0) }

    val colors = listOf(Color.Red, Color.Blue)

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Display Image
        if (product.images.isNotEmpty()) {
            // Ensure the selectedColorIndex is within bounds
            val imageUrl = product.images.getOrElse(selectedColorIndex) { product.images.first() }
            val processedImageUrl = imageUrl.replace("http://localhost:4000", "http://192.168.1.5:4000")

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(460.dp)
            ) {
                Image(
                    painter = rememberImagePainter(processedImageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .align(alignment = Alignment.TopEnd)
                        .width(330.dp)
                        .fillMaxHeight() // Make Image fill the Box
                        .clip(RoundedCornerShape(bottomStart = 26.dp)),
                    contentScale = ContentScale.Crop // Adjust scaling as needed
                )
            }
        } else {
            // Fallback if no images are available
            Text("No images available")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Color Options
        Row {
            colors.forEachIndexed { index, color ->
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .padding(4.dp)
                        .background(color)
                        .clickable { selectedColorIndex = index }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Display Product Details
        Text(
            text = product.name,
            fontFamily = NunitoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$ ${product.new_price}",
            fontFamily = NunitoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Description:",
            fontFamily = NunitoFont,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = product.description,
            fontFamily = NunitoFont,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}