package it.unibo.trace.data

import androidx.compose.ui.graphics.ImageBitmap
import it.unibo.trace.R

data class GroupMock(
    val id: String,
    val title: String,
    val imageRes: Int = R.drawable.ic_launcher_foreground,
    val imageBitmap: ImageBitmap? = null
)

data class ParticipantMock(
    val id: String,
    val name: String
)

val groupsMock = listOf(
    GroupMock(id = "g1", title = "Tokyo Trip"),
    GroupMock(id = "g2", title = "Weekend Milano"),
    GroupMock(id = "g3", title = "Road Trip")
)

val defaultParticipants = listOf(
    ParticipantMock(id = "you", name = "You"),
    ParticipantMock(id = "maria", name = "Maria"),
    ParticipantMock(id = "luca", name = "Luca"),
    ParticipantMock(id = "anna", name = "Anna")
)
