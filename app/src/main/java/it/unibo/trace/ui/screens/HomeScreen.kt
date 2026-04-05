package it.unibo.trace.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import it.unibo.trace.ui.composables.Footer
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import it.unibo.trace.ui.composables.Header

@Composable
fun HomeScreen(
    onAddClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val title = "TRACE"

    Scaffold(
        topBar = {
            Header(title, onIconClick = onSettingsClick)
        },
        bottomBar = {
            Footer(title)
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                onClick = onAddClick,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        // Content goes here
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            // Screen content
        }
    }
}
