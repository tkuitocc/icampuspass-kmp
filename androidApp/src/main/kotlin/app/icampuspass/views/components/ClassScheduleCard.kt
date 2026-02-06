package app.icampuspass.views.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE
import androidx.compose.ui.unit.dp
import app.icampuspass.views.theme.Theme

enum class ClassScheduleCardValue {
    Ended,
    Current,
    Upcoming,
}

class ClassScheduleCardState(
    initialValue: ClassScheduleCardValue
) {
    var currentValue: ClassScheduleCardValue by mutableStateOf(initialValue)

    val isEnded: Boolean
        get() = currentValue == ClassScheduleCardValue.Ended

    val isCurrent: Boolean
        get() = currentValue == ClassScheduleCardValue.Current

    val isUpcoming: Boolean
        get() = currentValue == ClassScheduleCardValue.Upcoming

    fun upcoming() {
        currentValue = ClassScheduleCardValue.Upcoming
    }

    fun current() {
        currentValue = ClassScheduleCardValue.Current
    }

    fun ended() {
        currentValue = ClassScheduleCardValue.Ended
    }
}

@Composable
fun rememberClassScheduleCardState(
    initialValue: ClassScheduleCardValue
): ClassScheduleCardState {
    return remember { ClassScheduleCardState(initialValue = initialValue) }
}

@Composable
fun ClassScheduleCard(
    modifier: Modifier = Modifier,
    cardState: ClassScheduleCardState =
        rememberClassScheduleCardState(initialValue = ClassScheduleCardValue.Current)
) {
    when {
        cardState.isEnded -> FilledCardExample()
        cardState.isCurrent -> OutlinedCardExample()
        cardState.isUpcoming -> ElevatedCardExample()
        else -> ErrorCardExample()
    }
}

@PreviewLightDark
@Composable
fun ErrorCardExample() {
    Theme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 100.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.errorContainer,
            )
        ) {
            Text(
                text = "Error",
                modifier = Modifier
                    .padding(all = 16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun FilledCardExample() {
    Theme {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 100.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        ) {
            Text(
                text = "Filled",
                modifier = Modifier
                    .padding(all = 16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun ElevatedCardExample() {
    Theme {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 100.dp),
            colors = CardDefaults.elevatedCardColors(
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
            )
        ) {
            Text(
                text = "Elevated",
                modifier = Modifier
                    .padding(all = 16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@PreviewLightDark
@Composable
fun OutlinedCardExample() {
    Theme {
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 100.dp),
            colors = CardDefaults.outlinedCardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text(
                text = "Outlined",
                modifier = Modifier
                    .padding(all = 16.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(name = "Light")
@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL)
@PreviewDynamicColors
@Preview(
    name = "Dark red",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    wallpaper = RED_DOMINATED_EXAMPLE
)
@Preview(
    name = "Dark blue",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    wallpaper = BLUE_DOMINATED_EXAMPLE
)
@Preview(
    name = "Dark green",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    wallpaper = GREEN_DOMINATED_EXAMPLE
)
@Preview(
    name = "Dark yellow",
    uiMode = UI_MODE_NIGHT_YES or UI_MODE_TYPE_NORMAL,
    wallpaper = YELLOW_DOMINATED_EXAMPLE
)
@Preview
@Composable
private fun ClassScheduleCardPreview() {
    Theme {
        val classScheduleCardState =
            rememberClassScheduleCardState(initialValue = ClassScheduleCardValue.Upcoming)

        Column {
            FilledCardExample()
            Spacer(modifier = Modifier.height(height = 12.dp))
            OutlinedCardExample()
            Spacer(modifier = Modifier.height(height = 12.dp))
            ElevatedCardExample()
            Spacer(modifier = Modifier.height(height = 12.dp))
            ElevatedCardExample()

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 4.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Button(
                    onClick = { when {
                        classScheduleCardState.isUpcoming -> classScheduleCardState.current()
                        classScheduleCardState.isCurrent -> classScheduleCardState.ended()
                        else -> classScheduleCardState.upcoming()
                    } }
                ) {
                    Text(text = "Change card state!")
                }

                ClassScheduleCard(cardState = classScheduleCardState)
            }
        }
    }
}

