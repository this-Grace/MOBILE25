package it.unibo.trace.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import it.unibo.trace.R
import it.unibo.trace.data.profileMock
import it.unibo.trace.ui.composables.CardRow
import it.unibo.trace.ui.composables.Footer
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.ImageCard
import it.unibo.trace.ui.composables.Section

@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit = {}
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    Scaffold(
        topBar = {
            Header(
                "PROFILE",
                imageVector = Icons.Default.Settings,
                onBackClick = {},
                onIconClick = onSettingsClick
            )
        },
        bottomBar = {
            Footer()
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .blur(radius = 15.dp)
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)
                        )
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "@${profileMock.username}",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Section(title = "Refund Details", onViewAllClick = null) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    CardRow(
                        title = "IBAN",
                        subtitle = profileMock.iban,
                        leadingContent = {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.ContentCopy,
                                    contentDescription = "copy",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        onClick = {
                            clipboardManager.setText(AnnotatedString(profileMock.iban))
                            Toast.makeText(context, "Copy!", Toast.LENGTH_SHORT).show()
                        },
                        titleFontWeight = FontWeight.Bold,
                        subtitleStyle = MaterialTheme.typography.bodyMedium,
                        contentPadding = PaddingValues(16.dp)
                    )
                    CardRow(
                        title = "PAYPAL",
                        subtitle = profileMock.paypalAccount,
                        leadingContent = {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(8.dp)
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    Icons.Default.ContentCopy,
                                    contentDescription = "copy",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        },
                        onClick = {
                            clipboardManager.setText(AnnotatedString(profileMock.paypalAccount))
                            Toast.makeText(context, "Copy!", Toast.LENGTH_SHORT).show()
                        },
                        titleFontWeight = FontWeight.Bold,
                        subtitleStyle = MaterialTheme.typography.bodyMedium,
                        contentPadding = PaddingValues(16.dp)
                    )
                }
            }

            Section(
                title = "Statistics",
                onViewAllClick = { /* TODO: Navigate stats */ }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    CardRow(
                        title = "TOTAL EXPENSES",
                        subtitle = profileMock.totalExpenses,
                        modifier = Modifier.weight(1f),
                        titleFontWeight = FontWeight.Bold,
                        subtitleStyle = MaterialTheme.typography.bodyMedium,
                        contentPadding = PaddingValues(16.dp)
                    )
                    CardRow(
                        title = "TOTAL TRIPS",
                        subtitle = profileMock.totalTrips,
                        modifier = Modifier.weight(1f),
                        titleFontWeight = FontWeight.Bold,
                        subtitleStyle = MaterialTheme.typography.bodyMedium,
                        contentPadding = PaddingValues(16.dp)
                    )
                }
            }

            Section(
                title = "Your Likes",
                onViewAllClick = { /* TODO: Navigate all likes */ }
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3) {
                        ImageCard(
                            imagePainter = painterResource(id = R.drawable.ic_launcher_foreground),
                            modifier = Modifier.size(100.dp),
                            isLiked = true,
                            showLikeIcon = true,
                            onLikeClick = { /* TODO: remove like and update UI */ },
                            onClick = { /* TODO: photo details */ }
                        )
                    }
                }
            }
        }
    }
}
