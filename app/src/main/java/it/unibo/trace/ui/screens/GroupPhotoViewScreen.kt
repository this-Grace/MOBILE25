package it.unibo.trace.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.unibo.trace.R
import it.unibo.trace.ui.composables.FloatingButton
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.NavigationBar
import it.unibo.trace.ui.composables.PhotoCard

data class PhotoMock(
    val id: Int,
    val authorName: String,
    val isLiked: Boolean,
    val aspectRatio: Float,
    val imageRes: Int
)

@Composable
fun GroupPhotoViewScreen(
    onFloatingClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {}
) {
    val title = "Weekend a Tokyo"

    val mockPhotos = remember {
        listOf(
            PhotoMock(1, "IO", false, 1.5f, R.drawable.ic_launcher_foreground),
            PhotoMock(2, "IO", true, 1.0f, R.drawable.ic_launcher_foreground),
            PhotoMock(3, "GIULIA", false, 1.0f, R.drawable.ic_launcher_foreground),
            PhotoMock(4, "LUCA", false, 0.7f, R.drawable.ic_launcher_foreground),
            PhotoMock(5, "LUCA", false, 1.5f, R.drawable.ic_launcher_foreground),
            PhotoMock(6, "TU", true, 0.7f, R.drawable.ic_launcher_foreground),
        )
    }

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
                imageVector = Icons.Default.Add,
                contentDescription = "AddPhoto"
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 8.dp
            ),
            verticalItemSpacing = 12.dp,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = mockPhotos,
                key = { it.id }
            ) { photo ->
                PhotoCard(
                    photoPainter = painterResource(id = photo.imageRes),
                    authorName = photo.authorName,
                    isLiked = photo.isLiked,
                    aspectRatio = photo.aspectRatio,
                    onLikeClick = { },
                    onClick = { }
                )
            }
        }
    }
}