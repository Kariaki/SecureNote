package com.getontop.candidate.kariaki.securenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.BiometricRoute
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.CreateNoteRoute
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.NotesScreen
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.Routes
import com.getontop.candidate.kariaki.securenote.presentation.ui.theme.AssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    startScreen()
                }
            }
        }


    }
    @Composable
    fun startScreen(){
        val navController = rememberNavController()
        NavHost(navController, startDestination = Routes.biometric) {
            composable(Routes.biometric){ BiometricRoute(navController = navController, activity = this@MainActivity)}
            composable(Routes.home) { NotesScreen(navController) }
            composable(Routes.createNote) { CreateNoteRoute(navController) }
        }
    }
}


