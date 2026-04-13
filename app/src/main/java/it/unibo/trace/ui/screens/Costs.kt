package it.unibo.trace.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.unibo.trace.R
import it.unibo.trace.ui.composables.FloatingButton
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.ImageCard
import it.unibo.trace.ui.composables.NavigationBar

@Composable
fun CostsScreen(
    onFloatingClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val title = "A Weekend in Tokyo"

    Scaffold(
        topBar = {
            Header(title, onIconClick = onSettingsClick)
        },
        bottomBar = {
            NavigationBar()
        },
        floatingActionButton = {
            FloatingButton(
                onClick = onFloatingClick,
                imageVector = Icons.Default.AddAPhoto,
                contentDescription = "AddAPhoto"
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(5) { index ->
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
