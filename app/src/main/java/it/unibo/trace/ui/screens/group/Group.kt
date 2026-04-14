package it.unibo.trace.ui.screens.group

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import it.unibo.trace.ui.composables.FloatingButton
import it.unibo.trace.ui.composables.NavigationBar
import it.unibo.trace.ui.composables.NavigationTabData
import it.unibo.trace.ui.screens.group.composables.Debit
import it.unibo.trace.ui.screens.group.composables.History
import it.unibo.trace.ui.screens.group.composables.Maps
import it.unibo.trace.ui.screens.group.composables.Photo

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreen(
    title: String = "GROUP NAME",
    onFloatingClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableIntStateOf(0) }

    val navItems = listOf(
        NavigationTabData(Icons.Default.Money, "DEBIT", 0) { padding ->
            Debit(innerPadding = padding)
        },
        NavigationTabData(Icons.Default.ShoppingCart, "HISTORY", 1) { padding ->
            History(innerPadding = padding)
        },
        NavigationTabData(Icons.Default.CameraAlt, "PHOTOS", 2) { padding ->
            Photo(innerPadding = padding)
        },
        NavigationTabData(Icons.Default.Place, "MAPS", 3) { padding ->
            Maps(innerPadding = padding)
        }
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        title,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Go Back */ }) {
                        Icon(
                            Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            Icons.Default.Groups,
                            contentDescription = "GroupData",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        bottomBar = {
            NavigationBar(
                items = navItems,
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        floatingActionButton = {
            FloatingButton(
                onClick = onFloatingClick,
                imageVector = Icons.Default.Add,
                contentDescription = "Add Photo"
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        navItems.find { it.index == selectedTab }?.content?.invoke(innerPadding)
    }
}
