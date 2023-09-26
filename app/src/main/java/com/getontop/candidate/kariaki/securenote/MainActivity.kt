package com.getontop.candidate.kariaki.securenote

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.getontop.candidate.kariaki.securenote.domain.dto.NoteDto
import com.getontop.candidate.kariaki.securenote.presentation.ui.components.NoteComponent
import com.getontop.candidate.kariaki.securenote.presentation.ui.routes.NotesScreen
import com.getontop.candidate.kariaki.securenote.presentation.ui.theme.AssessmentTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteDto = NoteDto("Gitlab password","I'm tired of everyting you guys are doing here and I'm going home",0,0,"Sept 14")
        setContent {
            AssessmentTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NotesScreen()
                }
            }
        }
    }
}


