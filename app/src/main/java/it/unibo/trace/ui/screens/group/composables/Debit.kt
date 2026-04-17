package it.unibo.trace.ui.screens.group.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import it.unibo.trace.data.participantsBalanceMock
import it.unibo.trace.data.personalBalanceMock
import it.unibo.trace.ui.composables.BalanceRow

@Composable
fun Debit(innerPadding: PaddingValues = PaddingValues()) {
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
            BalanceRow(
                title = "Saldo personale",
                amount = personalBalanceMock.amount,
                status = personalBalanceMock.status
            )
        }
        item {
            Text(
                text = "Partecipanti",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
            )
        }
        items(participantsBalanceMock, key = { it.id }) { participant ->
            BalanceRow(
                title = participant.name,
                amount = participant.amount
            )
        }
    }
}
