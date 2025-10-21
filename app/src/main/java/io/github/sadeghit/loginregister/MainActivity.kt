package io.github.sadeghit.loginregister

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import io.github.sadeghit.loginregister.navigation.SetupNavigation
import io.github.sadeghit.loginregister.ui.screen.LoginScreen
import io.github.sadeghit.loginregister.ui.theme.LoginRegisterTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            LoginRegisterTheme {
                SetupNavigation()

            }
        }
    }
}

