import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
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
        title = "Advent of Code"
    ) {
        App()
    }
}
