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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.unibo.trace.data.GroupMock
import it.unibo.trace.ui.composables.FloatingButton
import it.unibo.trace.ui.composables.Footer
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.ImageCard

@Composable
fun HomeScreen(
    groups: List<GroupMock>,
    onGroupClick: (String) -> Unit = {},
    onCreateGroup: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            Header("MY GROUPS", imageVector = Icons.Default.Person, onIconClick = onSettingsClick)
        },
        bottomBar = {
            Footer()
        },
        floatingActionButton = {
            FloatingButton(
                onClick = onCreateGroup,
                imageVector = Icons.Default.Add,
                contentDescription = "Add"
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            groups.forEach { group ->
                val painter = group.imageBitmap?.let { BitmapPainter(it) }
                    ?: painterResource(id = group.imageRes)
                ImageCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    title = group.title,
                    subtitle = "Tap to open",
                    imagePainter = painter,
                    showLikeIcon = false,
                    onClick = { onGroupClick(group.id) }
                )
            }
        }
    }
}
