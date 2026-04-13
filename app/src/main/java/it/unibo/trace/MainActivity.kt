package it.unibo.trace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import it.unibo.trace.ui.screens.CostsScreen
import it.unibo.trace.ui.screens.HomeScreen
import it.unibo.trace.ui.screens.LoginScreen
import it.unibo.trace.ui.screens.PhotoScreen
import it.unibo.trace.ui.screens.ProfileScreen
import it.unibo.trace.ui.screens.RegisterScreen
import it.unibo.trace.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                // LoginScreen {  }
                // RegisterScreen {  }
                // HomeScreen {  }
                // ProfileScreen {  } // TODO: fix section, parameter/mockup etc
                // CostsScreen {  } // TODO: parameter/mockup
                // PhotoScreen(2) // TODO: fix
            }
        }
    }
}
