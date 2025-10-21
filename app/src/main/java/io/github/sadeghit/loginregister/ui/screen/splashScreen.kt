package io.github.sadeghit.loginregister.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.sadeghit.loginregister.navigation.Screens
import io.github.sadeghit.loginregister.viewModel.LoginViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    // انیمیشن Fade in برای متن
    var visible by remember { mutableStateOf(false) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1500)
    )

    // شروع انیمیشن و هدایت به صفحه بعد
    LaunchedEffect(Unit) {
        visible = true
        delay(3000) // مدت زمان نمایش Splash
        val loggedIn = viewModel.checkLoginStatus()
        if (loggedIn) {
            navController.navigate(Screens.PostList.route) {
                popUpTo(Screens.SplashScreen.route) { inclusive = true }
            }
        } else {
            navController.navigate(Screens.Login.route) {
                popUpTo(Screens.SplashScreen.route) { inclusive = true }
            }
        }
    }

    // UI SplashScreen
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFFb81734), Color(0xFF311a38))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "CREATED by SADEGH IT",
            color = Color.White,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.alpha(alpha)
        )
    }
}
