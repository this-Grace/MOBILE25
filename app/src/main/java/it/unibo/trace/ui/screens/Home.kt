package it.unibo.trace.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import it.unibo.trace.ui.composables.Footer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.unibo.trace.R
import it.unibo.trace.ui.composables.FloatingButton
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.ImageCard

@Composable
fun HomeScreen(
    onFloatingClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Header("MY GROUPS", onIconClick = onSettingsClick)
        },
        bottomBar = {
            Footer()
        },
        floatingActionButton = {
            FloatingButton(
                onClick = onFloatingClick,
                imageVector = Icons.Default.Add,
                contentDescription = "Add")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(5) { index ->
                ImageCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    title = "Card Random #${index + 1}",
                    subtitle = "Example of description",
                    imagePainter = painterResource(id = R.drawable.ic_launcher_foreground),
                    showLikeIcon = false,
                    onClick = { /* TODO: navigate in the group */ }
                )
            }
        }
    }
}
