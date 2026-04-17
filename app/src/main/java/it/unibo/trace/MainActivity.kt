package it.unibo.trace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import it.unibo.trace.data.defaultParticipants
import it.unibo.trace.ui.screens.group.ParticipantsScreen
import it.unibo.trace.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                // LoginScreen {  }
                // RegisterScreen {  }
                // HomeScreen(groupsMock)
                // ProfileScreen { }
                // CreateGroupScreen()
                ParticipantsScreen("Group", defaultParticipants)
            }
        }
    }
}
