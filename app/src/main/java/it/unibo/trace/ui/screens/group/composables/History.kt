package it.unibo.trace.ui.screens.group.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsSubway
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unibo.trace.data.ExpenseMock
import it.unibo.trace.data.participantsBalanceMock
import it.unibo.trace.ui.composables.BalanceRow
import it.unibo.trace.ui.composables.CardRow
import it.unibo.trace.ui.composables.SwipeableCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun History(
    innerPadding: PaddingValues = PaddingValues(),
    expenses: List<ExpenseMock>,
    onEditExpense: (ExpenseMock) -> Unit,
    onDeleteExpense: (ExpenseMock) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = innerPadding.calculateTopPadding() + 8.dp,
            bottom = innerPadding.calculateBottomPadding() + 8.dp,
            start = 16.dp,
            end = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Saldo partecipanti",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
        }
        items(participantsBalanceMock, key = { "history_${it.id}" }) { participant ->
            BalanceRow(
                title = participant.name,
                amount = participant.amount
            )
        }

        val groupedExpenses = expenses.groupBy { it.date }

        groupedExpenses.forEach { (date, expenses) ->
            stickyHeader {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        text = date.uppercase(),
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(vertical = 12.dp)
                    )
                }
            }

            items(expenses) { expense ->
                SwipeableCard(
                    onDelete = { onDeleteExpense(expense) },
                    onEdit = { onEditExpense(expense) }
                ) {
                    CardRow(
                        title = expense.title,
                        subtitle = "${expense.category} • Paid by ${expense.paidBy}",
                        leadingContent = {
                            Icon(
                                imageVector = when (expense.iconType) {
                                    "restaurant" -> Icons.Default.Restaurant
                                    "subway" -> Icons.Default.DirectionsSubway
                                    else -> Icons.Default.ShoppingBag
                                },
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        trailingContent = {
                            Text(
                                text = "€${String.format("%.2f", expense.amount)}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    )
                }
            }
        }
    }
}
