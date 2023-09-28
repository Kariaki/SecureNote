package com.getontop.candidate.kariaki.securenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.BiometricRoute
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.CreateNoteRoute
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.DownloadNotes
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.NotesScreen
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.Routes
import com.getontop.candidate.kariaki.securenote.presentation.ui.theme.AssessmentTheme
import com.getontop.candidate.kariaki.securenote.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : FragmentActivity() {

    private  val viewModel:NoteViewModel by viewModels()
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
            composable(Routes.biometric){
                BiometricRoute(navController = navController, activity = this@MainActivity)
            }
            composable(Routes.download){
                DownloadNotes(
                    navController = navController
                )
            }
            composable(Routes.home) {
                NotesScreen(navController, noteViewModel = viewModel)
            }
            composable(Routes.createNote) {
                CreateNoteRoute(navController,viewModel)
            }
        }
    }
}


