package it.unibo.trace.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import it.unibo.trace.data.BalanceStatus
import it.unibo.trace.ui.theme.semanticColors
import java.util.Locale
import kotlin.math.abs

@Composable
fun BalanceRow(
    title: String,
    amount: Double,
    modifier: Modifier = Modifier,
    status: BalanceStatus? = null
) {
    val semantic = MaterialTheme.colorScheme.semanticColors

    val (label, color) = when (status) {
        BalanceStatus.RECEIVE -> "To receive" to semantic.positive
        BalanceStatus.PAY -> "To pay" to semantic.negative
        BalanceStatus.EVEN -> "Settled" to semantic.neutral
        null -> when {
            amount > 0 -> "Positive" to semantic.positive
            amount < 0 -> "Negative" to semantic.negative
            else -> "Settled" to semantic.neutral
        }
    }

    val signedAmount = when {
        amount > 0 -> "+$${formatAmount(amount)}"
        amount < 0 -> "-$${formatAmount(abs(amount))}"
        else -> "$0.00"
    }

    CardRow(
        title = title,
        subtitle = label,
        modifier = modifier,
        subtitleColor = color,
        titleFontWeight = FontWeight.Bold,
        trailingContent = {
            Text(
                text = signedAmount,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.ExtraBold,
                color = color
            )
        }
    )
}

private fun formatAmount(value: Double): String = String.format(Locale.ENGLISH, "%.2f", value)
