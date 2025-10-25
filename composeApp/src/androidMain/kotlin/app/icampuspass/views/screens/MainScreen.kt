package app.icampuspass.views.screens

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.QrCodeScanner
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.icampuspass.Greeting
import app.icampuspass.R
import app.icampuspass.viewmodels.MainViewModel
import app.icampuspass.views.theme.Theme
import icampuspass.composeapp.generated.resources.Res
import icampuspass.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onMenuOpen: () -> Unit = {},
    onNavigateUserPicker: () -> Unit = {}
) {
    val viewModel = koinViewModel<MainViewModel>()

    val greeting = remember { Greeting().greet() }

    var showContent by remember { mutableStateOf(value = false) }

    Scaffold(
        topBar = {
            Column {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(
                                id = R.string.top_app_bar_title,
                                stringResource(id = R.string.top_app_bar_title_unit_tku)
                            )
                        )
                    },
                    navigationIcon = {
                        TooltipBox(
                            positionProvider = TooltipDefaults
                                .rememberPlainTooltipPositionProvider(
                                    spacingBetweenTooltipAndAnchor = 16.dp
                                ),
                            tooltip = {
                                PlainTooltip(
                                    modifier = Modifier.padding(all = 8.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.menu_action_open),
                                        modifier = Modifier
                                            .padding(horizontal = 8.dp, vertical = 2.dp),
                                        style = MaterialTheme.typography.labelLarge
                                    )
                                }
                            },
                            state = rememberTooltipState()
                        ) {
                            IconButton(onClick = onMenuOpen) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = stringResource(id = R.string.menu_action_open)
                                )
                            }
                        }
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(
                                imageVector = Icons.Outlined.QrCodeScanner,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(all = 6.dp)
                            )
                        }

                        IconButton(onClick = onNavigateUserPicker) {
                            Icon(
                                imageVector = Icons.Outlined.AccountCircle,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(all = 4.dp)
                            )
                        }
                    }
                )

                HorizontalDivider(
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        },
        content = { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .consumeWindowInsets(paddingValues = innerPadding),
                contentPadding = innerPadding
            ) {
                item {
                    Text(
                        text = "Hello, Username!",
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 22.sp,
                            lineHeight = 28.sp,
                            letterSpacing = 0.sp
                        )
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 4.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(onClick = { showContent = !showContent }) {
                            Text(text = "Click me!")
                        }
                    }
                }

                item {
                    AnimatedVisibility(
                        visible = showContent,
                        modifier = Modifier.fillParentMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.fillParentMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

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
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Preview(
    showSystemUi = true,
    backgroundColor = 0xFF000000,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun MainScreenPreview() {
    Theme {
        Surface(color = Color.Black) {
            Surface(modifier = Modifier.safeDrawingPadding()) {
                MainScreen()
            }
        }
    }
}
