package it.unibo.trace.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PhotoCard(
    photoPainter: Painter,
    modifier: Modifier = Modifier,
    authorName: String? = null,
    isLiked: Boolean = false,
    showLikeIcon: Boolean = true,
    onLikeClick: () -> Unit = {},
    onClick: () -> Unit = {}
) {
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = photoPainter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.1f),
                                Color.Black.copy(alpha = 0.5f)
                            )
                        )
                    )
            )

            authorName?.let { name ->
                Text(
                    text = name.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp)
                )
            }

            if (showLikeIcon) {
                IconButton(
                    onClick = onLikeClick,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(4.dp)
                ) {
                    Icon(
                        imageVector = if (isLiked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (isLiked) Color.Red else Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}