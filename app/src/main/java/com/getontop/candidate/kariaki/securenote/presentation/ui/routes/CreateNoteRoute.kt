package com.getontop.candidate.kariaki.securenote.presentation.ui.routes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getontop.candidate.kariaki.securenote.core.spaceHeight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateNewRoute() {
    var titleText by remember {
        mutableStateOf("")
    }
    var descriptionText by remember {
        mutableStateOf("")
    }
    var displayCheck by remember {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
            AnimatedVisibility(visible = displayCheck) {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Check, contentDescription = null)
                }
            }
        }
        10.spaceHeight()
        Column(modifier = Modifier.padding(10.dp)) {
            TextField(
                value = titleText,
                onValueChange = {
                    titleText = it
                    if (it.isNotEmpty() && !displayCheck) {
                        displayCheck=true
                    }
                    if (it.isEmpty() && descriptionText.isEmpty()) {
                        displayCheck = false
                    }
                },
                placeholder = { Text(text = "Title", fontSize = 18.sp, color = Color.Gray) },
                modifier = Modifier.fillMaxWidth(),
                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black)
            )
            10.spaceHeight()
            TextField(value = descriptionText, onValueChange = {
                descriptionText = it
                if (it.isNotEmpty() && !displayCheck) {
                    displayCheck=true
                }
                if (it.isEmpty() && titleText.isEmpty()) {
                    displayCheck = false
                }
            }, placeholder = { Text(text = "Start typing...") }, modifier = Modifier.fillMaxWidth())
        }
    }

}
