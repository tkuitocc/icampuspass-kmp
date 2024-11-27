package com.itocc.icampuspass

import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

import com.itocc.icampuspass.components.Scaffold

@Preview
@Composable
fun App() {
    val drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)

    val scope: CoroutineScope = rememberCoroutineScope()

    MaterialTheme() {
        Scaffold(
            drawerState,
            scope
        )
    }
}