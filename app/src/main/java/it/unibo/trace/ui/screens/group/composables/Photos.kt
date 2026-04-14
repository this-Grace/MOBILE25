package it.unibo.trace.ui.screens.group.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.unibo.trace.data.photosMockup
import it.unibo.trace.ui.composables.ImageCard

data class PhotoMock(
    val id: Int,
    val authorName: String,
    val isLiked: Boolean,
    val imageRes: Int
)

@Composable
fun Photo(innerPadding: PaddingValues = PaddingValues()) {
    val photosState = remember {
        mutableStateListOf<PhotoMock>().apply { addAll(photosMockup) }
    }

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
            items = photosState,
            key = { it.id }
        ) { photo ->
            ImageCard(
                imagePainter = painterResource(id = photo.imageRes),
                modifier = Modifier.fillMaxWidth(),
                title = photo.authorName,
                isLiked = photo.isLiked,
                showLikeIcon = true,
                onLikeClick = {
                    val index = photosState.indexOfFirst { it.id == photo.id }
                    if (index != -1) {
                        photosState[index] = photosState[index].copy(isLiked = !photo.isLiked)
                    }
                },
                onClick = { /* Navigazione */ }
            )
        }
    }
}
