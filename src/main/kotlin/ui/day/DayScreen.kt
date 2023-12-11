package ui.day

import adventOfCode.AdventOfCodeDay
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DayScreen(
    day: AdventOfCodeDay,
    goBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                modifier = Modifier.size(24.dp)
                    .clickable { goBack() },
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
            )
            Text(
                modifier = Modifier.weight(1f),
                text = day.name,
                textAlign = TextAlign.Center,
                fontSize = 32.sp
            )
            Spacer(modifier = Modifier.width(24.dp))
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(20.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
        ) {
            items(day.puzzles) {
                Text(
                    textAlign = TextAlign.Start,
                    text = it.description,
                    fontSize = 22.sp
                )
                var showInput by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = "Input:",
                        fontSize = 22.sp,
                        color = Color.Red
                    )
                    Button(
                        onClick = { showInput = !showInput }
                    ) {
                        Text(
                            textAlign = TextAlign.Start,
                            text = if (showInput) "Hide input" else "Show input",
                            fontSize = 22.sp
                        )
                    }
                }
                if (showInput) {
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        textAlign = TextAlign.Start,
                        text = it.input,
                        fontSize = 22.sp
                    )
                }
                var showSolution by remember {
                    mutableStateOf(false)
                }
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        textAlign = TextAlign.Start,
                        text = "Solution:",
                        fontSize = 22.sp,
                        color = Color.Red
                    )
                    Button(
                        onClick = { showSolution = !showSolution }
                    ) {
                        Text(
                            textAlign = TextAlign.Start,
                            text = if (showSolution) "Hide solution" else "Show solution",
                            fontSize = 22.sp
                        )
                    }
                }
                if (showSolution) {
                    Text(
                        modifier = Modifier.padding(top = 12.dp),
                        textAlign = TextAlign.Start,
                        text = it.solution,
                        fontSize = 22.sp
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 12.dp),
                    textAlign = TextAlign.Start,
                    text = "Résultat: ${it.result}",
                    fontSize = 22.sp,
                    color = Color.Red
                )
            }
        }
    }
}