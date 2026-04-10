package it.unibo.trace.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.LocalMall
import androidx.compose.material.icons.rounded.Restaurant
import androidx.compose.material.icons.rounded.Train
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import it.unibo.trace.ui.composables.FloatingButton
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.InfoCard
import it.unibo.trace.ui.composables.NavigationBar
import it.unibo.trace.ui.composables.Section
import androidx.compose.foundation.Image
import androidx.compose.ui.Alignment

@Composable
fun CostsScreen(
    onFloatingClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
) {
    var selectedTab by remember { mutableIntStateOf(0) }
    val groupName = "Weekend in Tokyo"

    Scaffold(
        topBar = {
            Header(title = groupName, onIconClick = onSettingsClick)
        },
        bottomBar = {
            NavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        floatingActionButton = {
            FloatingButton(
                onClick = onFloatingClick,
                imageVector = Icons.Default.Add,
                contentDescription = "Add Expense"
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Image(
                    painter = painterResource(id = it.unibo.trace.R.drawable.ic_launcher_foreground),
                    contentDescription = "Group Image",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = groupName,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                )
            }

            Section(title = "Debts Summary") {
                InfoCard(
                    title = "Maria owes you",
                    value = "€120,00",
                )
                InfoCard(
                    title = "You owe Luca",
                    value = "€15,00",
                )
            }

            Section(title = "Today, Oct 14") {
                InfoCard(
                    title = "Sushiro Shinjuku",
                    subtitle = "Group dinner • Paid by you",
                    value = "€42,00",
                    icon = {
                        Icon(Icons.Rounded.Restaurant, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                    }
                )
                InfoCard(
                    title = "Metro pass",
                    subtitle = "Transportation • Paid by Maria",
                    value = "€22,50",
                    icon = {
                        Icon(Icons.Rounded.Train, contentDescription = null, tint = MaterialTheme.colorScheme.secondary)
                    }
                )
            }

            Section(title = "Yesterday, Oct 13") {
                InfoCard(
                    title = "Pokemon Cards",
                    subtitle = "Souvenir • Paid by you",
                    value = "€85,00",
                    icon = {
                        Icon(Icons.Rounded.LocalMall, contentDescription = null, tint = MaterialTheme.colorScheme.tertiary)
                    }
                )
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}