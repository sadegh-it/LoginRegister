package io.github.sadeghit.loginregister.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.sadeghit.loginregister.data.model.Post

import io.github.sadeghit.loginregister.navigation.Screens
import io.github.sadeghit.loginregister.viewModel.LoginViewModel
import io.github.sadeghit.loginregister.viewModel.PostViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostsScreen(
    viewModel: PostViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val posts by viewModel.posts.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState(initial = false)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Posts", fontWeight = FontWeight.Bold) },
                actions = {
                    IconButton(onClick = {
                        loginViewModel.logout()
                        navController.navigate(Screens.Login.route) {
                            popUpTo(Screens.PostList.route) { inclusive = true }
                        }
                    }) {
                        Icon(
                            Icons.Default.ExitToApp,
                            contentDescription = "Logout",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF3A0CA3),
                            Color(0xFF7209B7),
                            Color(0xFFF72585)
                        )
                    )
                )
                .padding(innerPadding)
        ) {
            when {
                isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }

                posts.isEmpty() -> {
                    Text(
                        "No posts found!",
                        color = Color.White,
                        fontSize = 18.sp,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }

                else -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp)
                    ) {
                        items(posts) { post ->
                            PostItem(post)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PostItem(post: Post) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .shadow(8.dp, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
            .clickable { /* TODO: navigate to details */ },
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.95f)
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = post.title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3A0CA3)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = post.body,
                fontSize = 15.sp,
                color = Color.DarkGray,
                lineHeight = 20.sp
            )
        }
    }
}
