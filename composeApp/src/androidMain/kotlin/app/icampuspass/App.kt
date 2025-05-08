package app.icampuspass

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.content.res.Configuration.UI_MODE_TYPE_NORMAL
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.tooling.preview.Wallpapers.BLUE_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.GREEN_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.RED_DOMINATED_EXAMPLE
import androidx.compose.ui.tooling.preview.Wallpapers.YELLOW_DOMINATED_EXAMPLE
import androidx.compose.ui.unit.dp
import app.icampuspass.views.theme.Theme

@Composable
fun App() {
    Theme {
        Column(
            modifier = Modifier
                .safeDrawingPadding()
                .fillMaxSize(),
        ) {
            // Your layout continues here
            ClassScheduleScreenTest()
        }
    }
}

@Composable fun MondayPage() { Text("This is Monday's schedule") }
@Composable fun TuesdayPage() { Text("This is Tuesday's schedule") }
@Composable fun WednesdayPage() { Text("This is Wednesday's schedule") }
@Composable fun ThursdayPage() { Text("This is Thursday's schedule") }
@Composable fun FridayPage() { Text("This is Friday's schedule") }

@Preview
@Composable
fun ClassScheduleScreenTest(modifier: Modifier = Modifier) {
    val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
    var selectedIndex by remember { mutableIntStateOf(0) }
    var previousIndex by remember { mutableIntStateOf(0) }

    val slideDirection = selectedIndex - previousIndex

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        // Top navigation
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
        ) {
            days.forEachIndexed { index, day ->
                Box(
                    modifier = Modifier
                        .width(56.dp)
                        .height(48.dp)
                        .clickable {
                            previousIndex = selectedIndex
                            selectedIndex = index
                        }
                ) {
                    Surface(
                        color = if (selectedIndex == index) Color.Gray else Color.LightGray,
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = day)
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Swipe + AnimatedContent
        AnimatedContent(
            targetState = days[selectedIndex],
            transitionSpec = {
                if (slideDirection >= 0) {
                    (slideInHorizontally { it } + fadeIn()).togetherWith(slideOutHorizontally { -it } + fadeOut())
                } else {
                    (slideInHorizontally { -it } + fadeIn()).togetherWith(slideOutHorizontally { it } + fadeOut())
                }
            },
            label = "SwipeSlide",
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(selectedIndex) {
                    detectHorizontalDragGestures { _, dragAmount ->
                        previousIndex = selectedIndex
                        if (dragAmount > 50 && selectedIndex > 0) {
                            selectedIndex--

                        } else if (dragAmount < -50 && selectedIndex < days.lastIndex) {
                            selectedIndex++

                        }
                    }
                }
        ) { day ->
            when (day) {
                "Mon" -> MondayPage()
                "Tue" -> TuesdayPage()
                "Wed" -> WednesdayPage()
                "Thu" -> ThursdayPage()
                "Fri" -> FridayPage()
            }
        }
    }
}
