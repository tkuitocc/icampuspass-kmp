package com.itocc.icampuspass.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope

import com.itocc.icampuspass.components.ScrollContent
import com.itocc.icampuspass.components.TopAppBar

@Preview
@Composable
fun Scaffold(
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Scaffold(
        // modifier: Modifier = Modifier,
        topBar = {
            TopAppBar(
                drawerState,
                scope
            )
        },
        // bottomBar: @Composable () -> Unit = {},
        // snackbarHost: @Composable () -> Unit = {},
        // floatingActionButton: @Composable () -> Unit = {},
        // floatingActionButtonPosition: FabPosition = FabPosition.End,
        // containerColor: Color = MaterialTheme.colorScheme. background,
        // contentColor: Color = contentColorFor(containerColor),
        // contentWindowInsets: WindowInsets = ScaffoldDefaults.contentWindowInsets,
        content = { innerPadding ->
            ModalNavigationDrawer(
                innerPadding,
                drawerState,
                scope
            )
        }
    )
}