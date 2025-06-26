package com.itocc.icampuspass.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.itocc.icampuspass.Greeting
import icampuspass.composeapp.generated.resources.Res
import icampuspass.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Preview
@Composable
fun ClassScheduleScreen(modifier: Modifier = Modifier) {
    var showContent by remember { mutableStateOf(value = false) }

    val greeting = remember {
        Greeting().greet()
    }

    Column(
        modifier = modifier
    ) {
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(onClick = { showContent = !showContent }) {
                Text(text = "Click me!")
            }

            AnimatedVisibility(showContent) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(resource = Res.drawable.compose_multiplatform),
                        contentDescription = null
                    )
                    Text(text = "Compose: $greeting")
                }
            }
        }
    }
}
