package br.com.gustavoalmeidacarvalho.solidariza.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.gustavoalmeidacarvalho.solidariza.ui.navigation.SolidarizaNavigation
import br.com.gustavoalmeidacarvalho.solidariza.ui.theme.SolidarizaTheme

class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition { viewModel.isLoading.value }
        setContent {
            SolidarizaApp()
        }
    }
}

@Composable
fun SolidarizaApp(
    navController: NavHostController = rememberNavController(),
) {
    SolidarizaTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            SolidarizaNavigation(navController = navController)
        }
    }
}