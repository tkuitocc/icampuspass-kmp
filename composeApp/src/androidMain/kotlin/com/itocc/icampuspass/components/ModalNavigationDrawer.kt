package com.itocc.icampuspass.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itocc.icampuspass.components.Scaffold
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Preview
@Composable
fun ModalNavigationDrawer(
    innerPadding: PaddingValues = PaddingValues(all = 0.dp),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed),
    scope: CoroutineScope = rememberCoroutineScope()
) {
    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet(
                drawerShape = RectangleShape
            ) {
                Column(
                    modifier = Modifier
                        .width(330.dp)
                        .padding(innerPadding)
                        .verticalScroll(state = rememberScrollState()),
                ) {
                    Text(
                        text = "Drawer Title",
                        modifier = Modifier
                            .padding(all = 16.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleLarge
                    )

                    HorizontalDivider()

                    Text(
                        text = "Section 1",
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(text = "Item 1")
                        },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(text = "Item 2")
                        },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        }
                    )

                    HorizontalDivider()

                    Text(
                        text = "Section 2",
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxWidth(),
                        style = MaterialTheme.typography.titleMedium
                    )

                    NavigationDrawerItem(
                        label = {
                            Text(text = "Settings")
                        },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = null
                            )
                        },
                        badge = {
                            Text(text = "20")
                        }
                    )

                    NavigationDrawerItem(
                        label = { Text("Help and feedback") },
                        selected = false,
                        onClick = {
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Settings,
                                contentDescription = null
                            )
                        },
                    )

                    Spacer(Modifier.height(height = 12.dp))
                }
            }
        },
        // modifier = Modifier.padding(top = 56.dp),
        drawerState = drawerState,
        gesturesEnabled = drawerState.isOpen,
        // scrimColor: Color = DrawerDefaults.scrimColor,
        content = {
            ScrollContent(
                innerPadding
            )
        }
    )
}