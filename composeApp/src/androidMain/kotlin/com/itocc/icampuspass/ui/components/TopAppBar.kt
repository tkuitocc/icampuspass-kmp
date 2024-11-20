package com.itocc.icampuspass.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

import com.itocc.icampuspass.ui.components.ModalNavigationDrawer
import icampuspass.composeapp.generated.resources.Res
import icampuspass.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopAppBar(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    TopAppBar(
        title = {
            Text("I'm a TopAppBar")
        },
        // modifier: Modifier = Modifier,
        navigationIcon = {
            IconButton(
                onClick = {
                    scope.launch {
                        if (drawerState.isOpen) {
                            drawerState.close()
                        } else {
                            drawerState.open()
                        }
                    }
                }
            ) {
                Icon(imageVector = Icons.Filled.Menu, null)
            }
        },
        actions = {
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.Share, null)
            }
            IconButton(
                onClick = {}
            ) {
                Icon(imageVector = Icons.Filled.Settings, null)
            }
        },
        // expandedHeight: Dp = TopAppBarDefaults.TopAppBarExpandedHeight,
        // windowInsets: WindowInsets = TopAppBarDefaults.windowInsets, was elevation = 4.dp
        // colors: TopAppBarColors = TopAppBarDefaults.topAppBarColors(),
        // scrollBehavior: TopAppBarScrollBehavior? = null
    )
}