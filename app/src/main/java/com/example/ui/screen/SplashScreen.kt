package com.example.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.domain.viewmodel.SplashViewModel

@Preview(showBackground = true)
@Composable
fun SplashScreen(viewModel: SplashViewModel? = null) {
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        viewModel?.getDeviceStatus()
    }

    Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                //verticalArrangement = Arrangement.Center, // Centrar verticalmente
                horizontalAlignment = Alignment.CenterHorizontally // Centrar horizontalmente
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text("Splash Screen", fontSize = 24.sp)
                }
                Spacer(modifier = Modifier.height(100.dp))
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(200.dp), // Tamaño del contenedor para el logo
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.gemini),
                        contentDescription = "Logo de la app",
                        modifier = Modifier.size(200.dp)
                    )
                }
                Spacer(modifier = Modifier.height(150.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isLoading) {
                        LinearProgressIndicator(
                            color = Color.Black,
                            trackColor = Color.LightGray,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        )
                    } else {
                        Text(
                            text = "Carga completada",
                            fontWeight = FontWeight(weight = 600),
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    )
}
