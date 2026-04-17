package it.unibo.trace.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.PrimaryButton

data class ExpenseForm(
    val amount: String,
    val title: String,
    val place: String,
    val category: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen(
    initialAmount: String = "",
    initialTitle: String = "",
    initialPlace: String = "",
    initialCategory: String = "Food",
    actionLabel: String = "ADD",
    onSave: (ExpenseForm) -> Unit,
    onBack: () -> Unit
) {
    var amount by remember { mutableStateOf(initialAmount) }
    var title by remember { mutableStateOf(initialTitle) }
    var place by remember { mutableStateOf(initialPlace) }
    var selectedCategory by remember { mutableStateOf(initialCategory) }
    val categories = listOf("Food", "Transport", "Shopping", "Bills", "Other")

    Scaffold(
        topBar = {
            Header("EXPENSE", onBackClick = onBack)
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    AmountInput(
                        amount = amount,
                        onAmountChange = { amount = it }
                    )
                }

                item {
                    InputField(
                        icon = Icons.Default.Description,
                        label = "What did you pay?",
                        value = title,
                        onValueChange = { title = it }
                    )
                }
                item {
                    InputField(
                        icon = Icons.Default.LocationOn,
                        label = "Add place",
                        value = place,
                        onValueChange = { place = it }
                    )
                }
                item {
                    CategorySelector(
                        categories = categories,
                        selectedCategory = selectedCategory,
                        onCategorySelected = { selectedCategory = it }
                    )
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Split with",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Icon(
                            Icons.Default.GroupAdd,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(16.dp)
                    ) {
                        DivisionOption(
                            "Select members",
                            "Seleziona chi partecipa",
                            Icons.Default.PersonAdd,
                            true,
                            showBg = false
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                            MemberAvatar("Io", true)
                            MemberAvatar("Marco", true)
                            MemberAvatar("Maria", false)
                            MemberAvatar("Luca", true)
                        }
                    }
                }
            }

            PrimaryButton(
                text = actionLabel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                onClick = {
                    onSave(
                        ExpenseForm(
                            amount = amount,
                            title = title,
                            place = place,
                            category = selectedCategory
                        )
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmountInput(
    amount: String,
    onAmountChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "AMOUNT",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        TextField(
            value = amount,
            onValueChange = { onAmountChange(normalizeAmountInput(it)) },
            modifier = Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            ),
            placeholder = {
                Text(
                    text = "0.00",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            prefix = {
                Text(
                    text = "€",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
    }
}

private fun normalizeAmountInput(value: String): String {
    val sanitized = value.replace(',', '.')
    val filtered = buildString {
        var hasDot = false
        for (char in sanitized) {
            when {
                char.isDigit() -> append(char)
                char == '.' && !hasDot -> {
                    append(char)
                    hasDot = true
                }
            }
        }
    }

    val parts = filtered.split('.', limit = 2)
    val integer = parts.getOrNull(0).orEmpty()
    val decimal = parts.getOrNull(1).orEmpty().take(2)
    return if (filtered.contains('.')) {
        val safeInt = if (integer.isEmpty()) "0" else integer
        "$safeInt.$decimal"
    } else {
        integer
    }
}

@Composable
fun CategorySelector(
    categories: List<String>,
    selectedCategory: String,
    onCategorySelected: (String) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Category",
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(Modifier.height(8.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(categories) { category ->
                FilterChip(
                    selected = category == selectedCategory,
                    onClick = { onCategorySelected(category) },
                    label = { Text(category) },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        selectedLabelColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                )
            }
        }
    }
}

@Composable
fun InputField(
    icon: ImageVector,
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant,
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
            TextField(
                value = value,
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = label,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    }
}

@Composable
fun DivisionOption(
    title: String,
    sub: String,
    icon: ImageVector,
    selected: Boolean,
    showBg: Boolean = true
) {
    val modifier = if (showBg) Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(16.dp))
        .background(MaterialTheme.colorScheme.surfaceVariant)
        .padding(16.dp)
    else Modifier.fillMaxWidth()

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant)
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = sub,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp
            )
        }
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(selectedColor = MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
fun MemberAvatar(name: String, isSelected: Boolean) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.BottomEnd) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape)
                    .border(
                        1.dp,
                        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant,
                        CircleShape
                    )
                    .background(MaterialTheme.colorScheme.surface)
            )
            if (isSelected) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(16.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant, CircleShape)
                )
            }
        }
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 10.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
