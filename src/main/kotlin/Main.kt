import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import navigation.AdventOfCodeNavHost

@Composable
@Preview
fun App() {
    MaterialTheme {
        AdventOfCodeNavHost()
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Advent of Code",
        state = rememberWindowState(
            position = WindowPosition(0.dp, 0.dp),
            size = DpSize.Unspecified
        )
    ) {
        App()
    }
}
