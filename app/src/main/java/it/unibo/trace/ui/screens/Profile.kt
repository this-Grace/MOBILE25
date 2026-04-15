package it.unibo.trace.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import it.unibo.trace.ui.composables.Footer
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.ImageCard
import it.unibo.trace.ui.composables.InfoCard
import it.unibo.trace.ui.composables.Section

@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit = {}
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current

    val username = "Marco_01"
    val iban = "IT 60 X 05424 03200 000000123456"
    val paypalAccount = "@marco_01"
    val totMoney = "€4.280"
    val totTrips = "12"

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
        }
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
                    text = "@$username",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Section(title = "Refund Details") {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    InfoCard(
                        title = "IBAN",
                        subtitle = iban,
                        icon = {
                            Icon(
                                Icons.Default.ContentCopy,
                                contentDescription = "copy",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        onClick = {
                            clipboardManager.setText(AnnotatedString(iban))
                            Toast.makeText(context, "Copy!", Toast.LENGTH_SHORT).show()
                        }
                    )
                    InfoCard(
                        title = "PAYPAL",
                        subtitle = paypalAccount,
                        icon = {
                            Icon(
                                Icons.Default.ContentCopy,
                                contentDescription = "copy",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        },
                        onClick = {
                            clipboardManager.setText(AnnotatedString(paypalAccount))
                            Toast.makeText(context, "Copy!", Toast.LENGTH_SHORT).show()
                        }
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
                    InfoCard(
                        title = "TOTAL EXPENSES",
                        subtitle = totMoney,
                        modifier = Modifier.weight(1f)
                    )
                    InfoCard(
                        title = "TOTAL TRIPS",
                        subtitle = totTrips,
                        modifier = Modifier.weight(1f)
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
