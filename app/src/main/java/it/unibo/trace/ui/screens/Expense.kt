package it.unibo.trace.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.GroupAdd
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import it.unibo.trace.ui.composables.Header
import it.unibo.trace.ui.composables.PrimaryButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpenseScreen() {
    var amount by remember { mutableStateOf("0.00") }

    Scaffold(
        topBar = {
            Header("PROFILE", onBackClick = {})
        },
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("AMOUNT", fontSize = 12.sp, color = Color.Gray)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                "€",
                                fontSize = 32.sp,
                                color = MaterialTheme.colorScheme.primary
                            )

                            Spacer(Modifier.width(8.dp))

                            Text(
                                text = amount,
                                fontSize = 64.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (amount.isEmpty())
                                    MaterialTheme.colorScheme.onBackground.copy(alpha = 0.4f)
                                else
                                    MaterialTheme.colorScheme.onBackground,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { /* apri tastiera custom */ }
                            )
                        }
                    }
                }

                item { InputField(Icons.Default.Description, "What did you pay?") }
                item { InputField(Icons.Default.LocationOn, "Add place") }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Split with", fontWeight = FontWeight.Bold)
                        Icon(Icons.Default.GroupAdd, contentDescription = null, tint = Color.Gray)
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(Color(0xFF1C1F26))
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
                text = "ADD",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                onClick = { /* TODO */ }
            )
        }
    }
}

@Composable
fun InputField(
    icon: ImageVector,
    label: String,
) {
    var text by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color(0xFF1C1F26),
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
                value = text,
                onValueChange = { text = it },
                placeholder = { Text(label, color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    cursorColor = Color(0xFF81D4FA)
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
        .background(Color(0xFF1C1F26))
        .padding(16.dp)
    else Modifier.fillMaxWidth()

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(Color(0xFF2C313C)),
            contentAlignment = Alignment.Center
        ) {
            Icon(icon, contentDescription = null, tint = Color.Gray)
        }
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(title, color = Color.White, fontWeight = FontWeight.Bold)
            Text(sub, color = Color.Gray, fontSize = 12.sp)
        }
        RadioButton(
            selected = selected,
            onClick = null,
            colors = RadioButtonDefaults.colors(selectedColor = Color(0xFF81D4FA))
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
                    .border(1.dp, if (isSelected) Color(0xFF81D4FA) else Color.Gray, CircleShape)
                    .background(Color(0xFF2C313C))
            )
            if (isSelected) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = null,
                    tint = Color(0xFF81D4FA),
                    modifier = Modifier
                        .size(16.dp)
                        .background(Color(0xFF1C1F26), CircleShape)
                )
            }
        }
        Text(name, color = Color.White, fontSize = 10.sp, modifier = Modifier.padding(top = 4.dp))
    }
}
