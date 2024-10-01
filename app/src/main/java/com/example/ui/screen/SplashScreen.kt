package com.example.ui.screen

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.example.domain.model.Device
import com.example.domain.viewmodel.SplashViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


@SuppressLint("CheckResult")
@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun SplashScreen(viewModel: SplashViewModel? = null) {
    var isLoading by remember { mutableStateOf(true) }
    var shouldShowDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        Observable.just(true)
            .subscribeOn(Schedulers.newThread())
            .map { viewModel?.getDeviceStatus()!! }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val device: Device? = it

                if (device != null) {
                    isLoading = false

                    Observable.just(true)
                        .delay(500, TimeUnit.MILLISECONDS)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe { viewModel?.navigateToApiKey() }

                } else {
                    shouldShowDialog = true
                }
            },
                // Manejar el error
                { error ->
                    shouldShowDialog = true
                }
            )
    }

    if (shouldShowDialog) {
        ErrorStartAppDialog(
            shouldShowDialog = shouldShowDialog,
            fn = {
                shouldShowDialog = it
            }
        )
    }

    return Scaffold(
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                //verticalArrangement = Arrangement.Center, // Centrar verticalmente
                horizontalAlignment = Alignment.CenterHorizontally, // Centrar horizontalmente
                content = {
                    Spacer(modifier = Modifier.height(100.dp))
                    Row(
                        modifier = Modifier
                            .padding(16.dp),
                        content = {
                            Text("Splash Screen", fontSize = 24.sp)
                        }
                    )
                    Spacer(modifier = Modifier.height(100.dp))
                    Box(
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp), // Tamaño del contenedor para el logo
                        contentAlignment = Alignment.Center,
                        content = {
                            Image(
                                painter = painterResource(id = R.drawable.gemini),
                                contentDescription = "Logo de la app",
                                modifier = Modifier.size(200.dp)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(150.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,
                        content = {
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
                    )
                }
            )
        }
    )
}


@Composable
fun ErrorStartAppDialog(shouldShowDialog: Boolean, fn: (Boolean) -> Unit) {
    if (shouldShowDialog) {
        AlertDialog(
            onDismissRequest = {
                fn(false)
            },
            title = { Text(text = "Alert Dialog") },
            text = { Text(text = "Jetpack Compose Alert Dialog") },
            confirmButton = { // 6
                Button(
                    onClick = {
                        fn(false)
                    },
                    content = {
                        Text(
                            text = "Confirm",
                            color = Color.White
                        )
                    }
                )
            }
        )
    }
}
