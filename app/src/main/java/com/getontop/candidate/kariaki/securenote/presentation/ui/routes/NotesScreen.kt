package com.getontop.candidate.kariaki.securenote.presentation.ui.routes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.getontop.candidate.kariaki.securenote.R
import com.getontop.candidate.kariaki.securenote.core.spaceHeight
import com.getontop.candidate.kariaki.securenote.core.spaceWidth
import com.getontop.candidate.kariaki.securenote.presentation.ui.components.NoteComponent
import com.getontop.candidate.kariaki.securenote.presentation.viewmodel.NoteViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(navController: NavController, noteViewModel: NoteViewModel = hiltViewModel()) {
    val noteList = noteViewModel.notes.collectAsState(initial = emptyList()).value
    val snackbarHostState = SnackbarHostState()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    noteViewModel.createOrUpdateNote()
                    navController.navigate(Routes.createNote)
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                },
                containerColor = Color.Black,
            )
        }, containerColor = Color.White,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        modifier = androidx.compose.ui.Modifier.padding(top = 10.dp),
        content = {
            it.calculateTopPadding()
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                ) {
                    Text(
                        "Notes",
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                    IconButton(
                        onClick = {
                                  navController.navigate(Routes.download)
                        },
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.outline_cloud_download_24),
                                contentDescription = null,
                                modifier = androidx.compose.ui.Modifier
                                    .height(30.dp)
                                    .width(30.dp)
                                    .padding(end = 5.dp)
                            )
                        })
                }
                10.spaceHeight()
                LazyColumn() {
                    items(noteList) { note ->
                        NoteComponent(note, onDelete = {
                            noteViewModel.deleteNote(note.id)
                        }) {
                            noteViewModel.createOrUpdateNote(note)
                            navController.navigate(Routes.createNote)
                        }
                    }
                }
            }
        }, floatingActionButtonPosition = FabPosition.End
    )
}