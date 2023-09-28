package com.getontop.candidate.kariaki.securenote.presentation.ui.routes

import android.content.Context
import android.net.Uri
import android.provider.CalendarContract.Colors
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.getontop.candidate.kariaki.securenote.R
import com.getontop.candidate.kariaki.securenote.core.DataState
import com.getontop.candidate.kariaki.securenote.core.spaceHeight
import com.getontop.candidate.kariaki.securenote.core.spaceWidth
import com.getontop.candidate.kariaki.securenote.presentation.viewmodel.NoteViewModel
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DownloadNotes(
    navController: NavController, noteViewModel: NoteViewModel = hiltViewModel()
) {
    val snackbarHostState = SnackbarHostState()


    val uploadState = noteViewModel.uploadNoteState.collectAsState().value
    val coroutine = rememberCoroutineScope()
    val context = LocalContext.current
    val mimTypeFilter = arrayOf("application/json")
    var uri: Uri? by remember {
        mutableStateOf(null)
    }

    var fileReadyText:String? by remember {
        mutableStateOf(null)
    }
    val selectFile = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = {
            if (it != null) {
                uri = it
                fileReadyText = "File ready"
                return@rememberLauncherForActivityResult
            }
            coroutine.launch {
                snackbarHostState.showSnackbar("Can not get data")
            }
        })

    Column(
        modifier = Modifier.padding(top = 10.dp),

    ){
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.popBackStack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                10.spaceWidth()
                Text(
                    "Download Notes",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    color = Color.Black
                )
            }
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                when(uploadState){
                    is DataState.Loading->
                        CircularProgressIndicator(
                            color = Color.Black,
                        )
                    is DataState.Success->
                        Text(text = "Notes uploaded")
                    else ->{
                        if(uploadState is DataState.Error){
                            Text(text = uploadState.message!!, style = TextStyle(color = Color.Red))
                            10.spaceHeight()
                        }
                        IconButton(
                            onClick = {
                                selectFile.launch(mimTypeFilter)
                            },
                            content = {
                                Icon(
                                    painter = painterResource(R.drawable.outline_cloud_download_24),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                )
                            })
                        5.spaceHeight()
                        Text(
                            fileReadyText?:"Click to select json file",
                            style = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Bold),
                            color = Color.Black
                        )
                        10.spaceHeight()
                        if(fileReadyText!=null) {
                            Button(onClick = {
                                if (uri != null) {
                                    noteViewModel.uploadNotes(uri!!, context = context)
                                }
                            }) {
                                Text(text = "Download")
                            }
                        }
                    }
                }

            }
        }
    }
}
