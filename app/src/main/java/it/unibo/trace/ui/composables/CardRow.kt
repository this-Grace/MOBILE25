@Composable
fun CardRow(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    onClick: (() -> Unit)? = null, // Reso opzionale
    enabled: Boolean = true,
    titleStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    titleFontWeight: FontWeight = FontWeight.Medium,
    titleColor: Color = MaterialTheme.colorScheme.onSurface,
    titleMaxLines: Int = 1,
    subtitleStyle: TextStyle = MaterialTheme.typography.bodySmall,
    subtitleColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    subtitleMaxLines: Int = 1,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
    elevation: CardElevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    val cardModifier = if (onClick != null) {
        modifier.fillMaxWidth().clickable(enabled = enabled, onClick = onClick)
    } else {
        modifier.fillMaxWidth()
    }

    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
        elevation = elevation,
        modifier = cardModifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            leadingContent?.invoke()

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = titleStyle,
                    fontWeight = titleFontWeight,
                    color = titleColor,
                    maxLines = titleMaxLines,
                    overflow = TextOverflow.Ellipsis
                )
                subtitle?.let {
                    Text(
                        text = it,
                        style = subtitleStyle,
                        color = subtitleColor,
                        maxLines = subtitleMaxLines,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            trailingContent?.invoke()
        }
    }
}
