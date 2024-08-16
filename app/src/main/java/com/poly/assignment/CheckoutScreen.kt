package com.poly.assignment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBackIosNew
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.poly.assignment.ui.theme.GelasioFont

@Composable
fun CheckoutScreen(navController: NavController, viewModel: ProductViewModel = viewModel()) {
    val cartProducts by viewModel.cartProducts.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()

    var address by remember { mutableStateOf("") }
    var paymentMethod by remember { mutableStateOf("") }
    val user = Firebase.auth.currentUser

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 16.dp, end = 16.dp, top = 36.dp, bottom = 1.dp)) {
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
                    text = "Check out",
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
        Row(modifier = Modifier
            .padding(top = 22.dp)
        ) {
            Text(text = "Shipping Address", color = Color(0xFF909090), fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(170.dp))
            Image(painter = painterResource(id = R.drawable.edit_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp))
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(135.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color(0XFFFFFFFF))
            .padding(top = 18.dp)
            .padding(horizontal = 16.dp),
        ) {
            Text(text = "Bruno Fernandes", fontSize = 23.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(15.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color(0xFFF0F0F0))
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "25 rue Robert Latouche, Nice, 06200, Côte", color = Color(0xFF808080))
            Text(text = "D'azur, France", color = Color(0xFF808080), modifier = Modifier.padding(bottom = 1.dp))

        }
        Row(modifier = Modifier
            .padding(top = 22.dp)
        ) {
            Text(text = "Payment", color = Color(0xFF909090), fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(260.dp))
            Image(painter = painterResource(id = R.drawable.edit_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp))
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(75.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color(0XFFFFFFFF))
            .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, // Căn giữa theo chiều dọc trong Row
                // Thêm khoảng đệm nếu cần
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(100.dp)
                        .height(40.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "**** **** **** 3947",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }

        }
        Row(modifier = Modifier
            .padding(top = 22.dp)
        ) {
            Text(text = "Delivery method", color = Color(0xFF909090), fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(260.dp))
            Image(painter = painterResource(id = R.drawable.edit_icon),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp))
        }
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .height(75.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .background(color = Color(0XFFFFFFFF))
            .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically, // Căn giữa theo chiều dọc trong Row
                // Thêm khoảng đệm nếu cần
            ) {
                Image(
                    painter = painterResource(id = R.drawable.deli),
                    contentDescription = "",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(115.dp)
                        .height(50.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Fast (2-3 days)",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 5.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .height(150.dp)
                .clip(shape = RoundedCornerShape(16.dp))
                .background(color = Color(0XFFFFFFFF))
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Order:", color = Color(0xFF808080), fontSize = 24.sp)
                Text(text = "$ ${totalPrice}", fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Delivery:", color = Color(0xFF808080), fontSize = 24.sp)
                Text(text = "$ 5", fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Total:", color = Color(0xFF808080), fontSize = 24.sp)
                Text(text = "$ ${totalPrice + 5}", fontSize = 21.sp, fontWeight = FontWeight.SemiBold)
            }
        }
        Spacer(modifier = Modifier.height(35.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .background(color = Color(0xFF242424))
            .clickable {
                navController.navigate("response")
            },
            contentAlignment = Alignment.Center
        ) {
            Text(text = "SUBMIT ORDER", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.SemiBold, textAlign = TextAlign.Center)
        }
    }
}

