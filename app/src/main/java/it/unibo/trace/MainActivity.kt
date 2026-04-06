package it.unibo.trace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import it.unibo.trace.ui.screens.HomeScreen
import it.unibo.trace.ui.screens.LoginScreen
import it.unibo.trace.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                var isLoggedIn by remember { mutableStateOf(false) }

                if (!isLoggedIn) {
                    LoginScreen(onLoginClick = { username, password ->
                        if (username.isNotEmpty() && password.isNotEmpty()) {
                            isLoggedIn = true
                        }
                    })
                } else {
                    HomeScreen()
                }
            }
        }
    }
}
