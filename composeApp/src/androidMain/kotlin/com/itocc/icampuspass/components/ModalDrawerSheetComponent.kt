package com.itocc.icampuspass.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.itocc.icampuspass.destinations.ClassScheduleDestination
import com.itocc.icampuspass.destinations.MainDestination
import icampuspass.composeapp.generated.resources.Res
import icampuspass.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Preview
@Composable
fun ModalDrawerSheetComponent(
    navController: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(DrawerValue.Closed)
) {
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
                        Text(text = "Home")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }

                        scope.launch {
                            navController.navigate(route = MainDestination) {
                                popUpTo(route = MainDestination) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
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
                        Text(text = "Class schedule")
                    },
                    selected = false,
                    onClick = {
                        scope.launch {
                            drawerState.close()
                        }

                        scope.launch {
                            navController.navigate(route = ClassScheduleDestination) {
                                popUpTo(route = MainDestination) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
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
}
