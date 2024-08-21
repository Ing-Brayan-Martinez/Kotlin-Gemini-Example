package com.example.ui.component.image

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.R

@Composable
fun DoneComponent() {
    Image(
        painter = painterResource(id = R.drawable.done),
        contentDescription = "Done",
        modifier = Modifier.height(90.dp)
    )
}

@Composable
fun SmallDoneComponent() {
    Image(
        painter = painterResource(id = R.drawable.done),
        contentDescription = "Done",
        modifier = Modifier.height(30.dp)
    )
}
