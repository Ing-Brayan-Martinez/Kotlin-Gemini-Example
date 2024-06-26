package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.presentation.screen.ScaffoldApiKey
import com.example.presentation.screen.ScaffoldChat
import com.example.presentation.screen.ScaffoldSplash
import com.example.presentation.theme.KotlinGeminiExampleTheme

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
                        ScaffoldSplash()
                    }

                    // Api Key
                    composable("api-key") {
                        ScaffoldApiKey()
                    }

                    // Chat
                    composable("chat") {
                        ScaffoldChat()
                    }
                }

            }
        }
    }
}
