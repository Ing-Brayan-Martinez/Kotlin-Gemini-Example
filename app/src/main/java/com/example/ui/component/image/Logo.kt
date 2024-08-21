package com.example.ui.component.image

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.R

@Composable
fun LogoComponent() {
    Image(
        painter = painterResource(id = R.drawable.gemini),
        contentDescription = "Done"
    )
}
