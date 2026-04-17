package it.unibo.trace.data

import androidx.compose.ui.graphics.ImageBitmap
import it.unibo.trace.R

data class PhotoItem(
    val id: String,
    val authorName: String,
    val isLiked: Boolean,
    val imageRes: Int,
    val imageBitmap: ImageBitmap? = null
)

val photosMockup = listOf(
    PhotoItem("1", "IO", false, R.drawable.ic_launcher_foreground),
    PhotoItem("2", "IO", true, R.drawable.ic_launcher_foreground),
    PhotoItem("3", "GIULIA", false, R.drawable.ic_launcher_foreground),
    PhotoItem("4", "LUCA", false, R.drawable.ic_launcher_foreground),
    PhotoItem("5", "LUCA", false, R.drawable.ic_launcher_foreground),
    PhotoItem("6", "TU", true, R.drawable.ic_launcher_foreground),
    PhotoItem("7", "TU", true, R.drawable.ic_launcher_foreground),
    PhotoItem("8", "TU", true, R.drawable.ic_launcher_foreground),
    PhotoItem("9", "TU", true, R.drawable.ic_launcher_foreground),
    PhotoItem("10", "TU", true, R.drawable.ic_launcher_foreground),
    PhotoItem("11", "TU", true, R.drawable.ic_launcher_foreground),
    PhotoItem("12", "TU", true, R.drawable.ic_launcher_foreground),
    PhotoItem("13", "TU", true, R.drawable.ic_launcher_foreground),
)
