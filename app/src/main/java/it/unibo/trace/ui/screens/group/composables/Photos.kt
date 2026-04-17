package it.unibo.trace.ui.screens.group.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.unibo.trace.data.PhotoItem
import it.unibo.trace.ui.composables.ImageCard

@Composable
fun Photo(
    innerPadding: PaddingValues = PaddingValues(),
    photos: List<PhotoItem>,
    onToggleLike: (String) -> Unit
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(160.dp),
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
            items = photos,
            key = { it.id }
        ) { photo ->
            val painter = photo.imageBitmap?.let { BitmapPainter(it) }
                ?: painterResource(id = photo.imageRes)
            ImageCard(
                imagePainter = painter,
                modifier = Modifier.fillMaxWidth(),
                title = photo.authorName,
                isLiked = photo.isLiked,
                showLikeIcon = true,
                onLikeClick = {
                    onToggleLike(photo.id)
                },
                onClick = { /* Navigazione */ }
            )
        }
    }
}
