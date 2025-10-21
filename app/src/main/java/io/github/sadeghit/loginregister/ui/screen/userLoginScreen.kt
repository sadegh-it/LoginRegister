/*
package io.github.sadeghit.loginregister.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import io.github.sadeghit.loginregister.navigation.Screens
import io.github.sadeghit.loginregister.viewModel.LoginViewModel

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val focusManager = LocalFocusManager.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val state by viewModel.stateLogin.collectAsState()
    val massage = viewModel.massage
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xffb81734), Color(0xff311a38))
                )
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ){    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    listOf(
                        Color(0xffb81734), Color(0xff311a38)
                    )
                )
            ),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 130.dp, start = 138.dp)
        ) {
            Text("Hello", fontSize = 50.sp, color = Color.White)
            Text("Sign in!", fontSize = 50.sp, color = Color.White) }

        Spacer(modifier = Modifier.height(70.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            placeholder = { Text("Username") },
            leadingIcon = { Icon(Icons.Default.Person, null) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )

        Spacer(Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            placeholder = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Key, null) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            {
                viewModel.login(username, password)
            }, modifier = Modifier
                .width(240.dp)
                .border(
                    width = 1.dp,
                    color = Color.White,
                    shape = RoundedCornerShape(50.dp)
                ), colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) { Text("SIGN IN", color = Color.White, fontSize = 20.sp) }

        Spacer(modifier = Modifier.height(80.dp))
        Row {
            Text("Don't have an account ?", fontSize = 14.sp, color = Color.White)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "SIGN UP",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.clickable {
                    navController.navigate(Screens.Register.route)
                })
        }


        if (state) navController.navigate(Screens.PostList.route) {
            popUpTo(Screens.Login.route) {
                inclusive = true
            }
        }

        LaunchedEffect(Unit) {
            massage.collect { Toast.makeText(context, it, Toast.LENGTH_SHORT).show() }
        }


    } }
}


@Composable
fun RegisterScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navController: NavController
) {
    val focusManager = LocalFocusManager.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }
    val state by viewModel.stateRegister.collectAsState()
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.horizontalGradient(
                    listOf(Color(0xffb81734), Color(0xff311a38))
                )
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) {
                focusManager.clearFocus()
            }
    ) {Column( horizontalAlignment = Alignment.CenterHorizontally) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 100.dp, start = 100.dp)
        ) {
            Text("Sing up!", fontSize = 50.sp, color = Color.White)
            Text("Let's join whit us", fontSize = 30.sp, color = Color.White)
        }
        Spacer(Modifier.height(50.dp))

        TextField(
            value = username,
            onValueChange = { username = it },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            placeholder = { Text("Username") },
            leadingIcon = { Icon(Icons.Default.Person, null) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )

        Spacer(Modifier.height(20.dp))

        TextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            placeholder = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Key, null) },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            )
        )
        Spacer(Modifier.height(30.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    uncheckedColor = Color.LightGray,
                    checkmarkColor = Color.Black
                )
            )
            Text(
                "I agree with privacy policy",
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(40.dp))


        Button(
            onClick = { viewModel.register(username, password) },
            modifier = Modifier
                .width(240.dp)
                .border(
                    width = 1.dp,
                    color = if (isChecked) Color.White else Color.Gray, // رنگ Border متناسب با وضعیت
                    shape = RoundedCornerShape(50.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = if (isChecked) Color.White else Color.Gray,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = Color.Gray
            ),
            enabled = isChecked
        ) {
            Text("SIGN UP", fontSize = 20.sp)
        }


        Spacer(modifier = Modifier.height(80.dp))

        Row {
            Text("You already have an account ?", fontSize = 14.sp, color = Color.White)
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "SIGN IN",
                fontSize = 16.sp,
                color = Color.White,
                modifier = Modifier.clickable {
                    navController.navigate(Screens.Login.route)
                })
        }
        if (state) Toast.makeText(context, "ثبت نام انجام شد", Toast.LENGTH_SHORT).show()
    }}



}*/
