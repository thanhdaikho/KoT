package com.poly.assignment

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import android.view.WindowManager
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Chair
import androidx.compose.material.icons.filled.Desk
import androidx.compose.material.icons.filled.EventSeat
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Light
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Bed
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.Chair
import androidx.compose.material.icons.outlined.Desk
import androidx.compose.material.icons.outlined.EventSeat
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Light
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import com.poly.assignment.ui.theme.DomineFont
import com.poly.assignment.ui.theme.GelasioFont
import com.poly.assignment.ui.theme.NunitoFont


@Composable
fun HomeScreen(navController: NavController) {

    val context = LocalContext.current
    val activity = context as Activity

    var selectedCategoryTabIndex by rememberSaveable { mutableStateOf(0) }
    val iconResource = listOf(
        Icons.Outlined.StarOutline,
        Icons.Outlined.EventSeat,
        Icons.Outlined.Desk,
        Icons.Outlined.Chair,
        Icons.Outlined.Bed,
        Icons.Outlined.Light
    )
    val filledIcons = listOf(
        Icons.Filled.Star,
        Icons.Filled.EventSeat,  // Assuming you have filled versions for these icons
        Icons.Filled.Desk,
        Icons.Filled.Chair,
        Icons.Filled.Bed,
        Icons.Filled.Light
    )
    val categories = listOf("Popular", "Chair", "Table", "Armchair", "Bed", "Lamp")



    SideEffect {
        val window = activity.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.statusBarColor = Color.Transparent.toArgb()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = window.decorView.systemUiVisibility or
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 42.dp, start = 16.dp, end = 16.dp)) {
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
                    text = "Make home",
                    color = Color(0xFF909090),
                    fontSize = 21.sp,
                    fontFamily = DomineFont,
                )
                Text(
                    text = "BEAUTIFUL",
                    color = Color.Black,
                    fontWeight = FontWeight.W600,
                    fontSize = 24.sp,
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
                    .clickable {})
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 80.dp)) {
            ScrollableTabRow(
                selectedTabIndex = selectedCategoryTabIndex,
                indicator = { TabRowDefaults.Indicator(color = Color.Transparent) },
                divider = { },
                containerColor = Color.Transparent,
                edgePadding = 0.dp
            ) {
                categories.forEachIndexed { index, category ->
                    val icon = if (selectedCategoryTabIndex == index) filledIcons[index] else iconResource[index]
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    ) {
                        Tab(
                            icon = {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = null,
                                    modifier = Modifier.size(32.dp),
                                    tint = if (selectedCategoryTabIndex == index) Color.White else Color(0xFF909090)
                                )
                            },
                            selected = selectedCategoryTabIndex == index,
                            onClick = { selectedCategoryTabIndex = index },
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp)
                                .clip(shape = RoundedCornerShape(16.dp))
                                .background(
                                    if (selectedCategoryTabIndex == index) Color(0xFF303030) else Color(
                                        0xFFF3F3F3
                                    ),
                                    shape = RoundedCornerShape(12.dp)
                                )
                        )
                        Text(
                            text = category,
                            fontSize = 20.sp,
                            fontFamily = NunitoFont,
                            fontWeight = if (selectedCategoryTabIndex == index) FontWeight.W600 else FontWeight.Normal,
                            color = if (selectedCategoryTabIndex == index) Color.Black else Color(0xFF909090),
                            modifier = Modifier
                                .padding(top = 8.dp)
                        )
                    }
                }
            }
            when(selectedCategoryTabIndex) {
                0 -> TabContent { HomeMainSection(route = selectedCategoryTabIndex, navController) }
                1 -> TabContent { HomeMainSection(route = selectedCategoryTabIndex, navController) }
                2 -> TabContent { HomeMainSection(route = selectedCategoryTabIndex, navController) }
                3 -> TabContent { HomeMainSection(route = selectedCategoryTabIndex, navController) }
                4 -> TabContent { HomeMainSection(route = selectedCategoryTabIndex, navController) }
                5 -> TabContent { HomeMainSection(route = selectedCategoryTabIndex, navController) }
            }
        }

    }
}

@Composable
fun TabContent(content: @Composable () -> Unit) {
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 100.dp)) {
        content()
    }
}

