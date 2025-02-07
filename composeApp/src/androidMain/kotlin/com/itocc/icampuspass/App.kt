package com.itocc.icampuspass

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import icampuspass.composeapp.generated.resources.Res
import icampuspass.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun App() {
    val drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)

    val scope: CoroutineScope = rememberCoroutineScope()

    val navController = rememberNavController()

    val greeting = remember {
        Greeting().greet()
    }

    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) {
            darkColorScheme()
        } else {
            lightColorScheme()
        }
    ) {
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet(
                    drawerShape = RectangleShape
                ) {
                    Column(
                        modifier = Modifier
                            .width(330.dp)
                            .verticalScroll(state = rememberScrollState()),
                    ) {
                        // if isLoggedIn and hasAvatar
                        if (false) {
                            Image(
                                painter = painterResource(
                                    resource = Res.drawable.compose_multiplatform
                                ),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .padding(start = 24.dp, top = 24.dp, end = 16.dp, bottom = 6.dp)
                                    .size(64.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                            )
                        } else {
                            Icon(
                                imageVector = Icons.Filled.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .padding(start = 18.dp, top = 18.dp, end = 0.dp, bottom = 0.dp)
                                    .size(76.dp)
                                    .clip(CircleShape)
                            )
                        }

                        // if isLoggedIn
                        if (false) {
                            Text(
                                text = "Username",
                                modifier = Modifier
                                    .padding(start = 24.dp, top = 10.dp, end = 16.dp, bottom = 1.dp)
                                    .fillMaxWidth(),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    lineHeight = 28.sp,
                                    letterSpacing = 0.sp
                                )
                            )

                            Text(
                                text = "User ID",
                                modifier = Modifier
                                    .padding(start = 24.dp, top = 0.dp, end = 16.dp, bottom = 11.dp)
                                    .fillMaxWidth(),
                                style = MaterialTheme.typography.titleSmall
                            )
                        } else {
                            Text(
                                text = "Guest",
                                modifier = Modifier
                                    .padding(horizontal = 24.dp, vertical = 20.dp)
                                    .fillMaxWidth(),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 22.sp,
                                    lineHeight = 28.sp,
                                    letterSpacing = 0.sp
                                )
                            )
                        }

                        HorizontalDivider()

                        Spacer(Modifier.height(height = 8.dp))

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 0.dp)
                                .fillMaxWidth(),
                        ) {
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
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Settings,
                                        contentDescription = null
                                    )
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
                                },
                                icon = {
                                    Icon(
                                        imageVector = Icons.Outlined.Settings,
                                        contentDescription = null
                                    )
                                }
                            )
                        }

                        Spacer(Modifier.height(height = 8.dp))

                        HorizontalDivider()

                        Spacer(Modifier.height(height = 8.dp))

                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp, vertical = 0.dp)
                                .fillMaxWidth(),
                        ) {
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
                        }

                        Spacer(Modifier.height(height = 12.dp))
                    }
                }
            },
            modifier = Modifier.fillMaxSize().safeDrawingPadding(),
            drawerState = drawerState,
            gesturesEnabled = drawerState.isOpen,
            content = {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("iCampus Pass")
                            },
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
                                    Icon(imageVector = Icons.Filled.Settings, null)
                                }
                            }
                        )
                    },
                    content = { scaffoldInnerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(scaffoldInnerPadding)
                                .fillMaxSize()
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.Start
                            ) {
                                Text(
                                    text = "Hello, username!",
                                    modifier = Modifier.fillMaxWidth(),
                                    style = TextStyle(
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 22.sp,
                                        lineHeight = 28.sp,
                                        letterSpacing = 0.sp
                                    )
                                )
                            }
                        }
                    }
                )
            }
        )
    }
}
