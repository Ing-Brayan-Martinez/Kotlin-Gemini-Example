package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.viewmodel.ApiKeyViewModel
import com.example.domain.viewmodel.ChatViewModel
import com.example.domain.viewmodel.SplashViewModel
import com.example.ui.screen.ScaffoldApiKey
import com.example.ui.screen.ScaffoldChat
import com.example.ui.screen.ScaffoldSplash
import com.example.ui.theme.KotlinGeminiExampleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinGeminiExampleTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "splash"
                ) {
                    // Splash
                    composable("splash") {
                        val splashViewModel = SplashViewModel(applicationContext, navController)
                        ScaffoldSplash(splashViewModel)
                    }

                    // Api Key
                    composable("api-key") {
                        val apiKeyViewModel = ApiKeyViewModel(applicationContext, navController)
                        ScaffoldApiKey(apiKeyViewModel)
                    }

                    // Chat
                    composable("chat") {
                        val chatViewModel = ChatViewModel(applicationContext, navController)
                        ScaffoldChat(chatViewModel)
                    }
                }

            }
        }
    }
}
