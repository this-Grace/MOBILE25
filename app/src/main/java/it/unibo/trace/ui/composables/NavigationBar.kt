package it.unibo.trace.ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier

@Composable
fun NavigationBar() {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf(
        Icons.Default.Home to "Home",
        Icons.Default.List to "History",
        Icons.Default.Person to "Profile"
    )

    Column {
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)
        )
        NavigationBar(
            modifier = Modifier.height(100.dp),
            containerColor = MaterialTheme.colorScheme.background,
            tonalElevation = 0.dp
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = selectedItem == index,
                    onClick = { selectedItem = index },
                    icon = {
                        Icon(
                            imageVector = item.first,
                            contentDescription = item.second,
                            modifier = Modifier.size(28.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        indicatorColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    }
}
