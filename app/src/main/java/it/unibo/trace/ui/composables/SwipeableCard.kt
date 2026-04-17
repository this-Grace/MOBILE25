package it.unibo.trace.ui.composables

import androidx.compose.animation.core.tween
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

enum class DragAnchors {
    Closed,
    Open
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableCard(
    onDelete: () -> Unit,
    onEdit: () -> Unit
) {
    val shape = MaterialTheme.shapes.large
    val actionWidth = 160f
    val density = LocalDensity.current
    val actionWidthPx = with(density) { actionWidth.dp.toPx() }

    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val state = remember(density) {
        AnchoredDraggableState(
            initialValue = DragAnchors.Closed,
            positionalThreshold = { distance: Float -> distance * 0.5f },
            velocityThreshold = { with(density) { 100.dp.toPx() } },
            snapAnimationSpec = tween(),
            decayAnimationSpec = decayAnimationSpec
        )
    }

    SideEffect {
        state.updateAnchors(
            DraggableAnchors {
                DragAnchors.Closed at 0f
                DragAnchors.Open at -actionWidthPx
            }
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
            .clip(shape)
    ) {

        Row(
            modifier = Modifier
                .matchParentSize()
                .clip(shape),
            horizontalArrangement = Arrangement.End
        ) {

            IconButton(
                onClick = onEdit,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }

            IconButton(
                onClick = onDelete,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .background(MaterialTheme.colorScheme.error)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Delete")
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        x = state.offset.takeIf { !it.isNaN() }?.roundToInt() ?: 0,
                        y = 0
                    )
                }
                .anchoredDraggable(
                    state = state,
                    orientation = Orientation.Horizontal
                )
        ) {
            Text(
                "Something...",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
