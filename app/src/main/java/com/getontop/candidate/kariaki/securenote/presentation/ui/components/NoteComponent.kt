package com.getontop.candidate.kariaki.securenote.presentation.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getontop.candidate.kariaki.securenote.R
import com.getontop.candidate.kariaki.securenote.core.spaceHeight
import com.getontop.candidate.kariaki.securenote.domain.dto.NoteDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteComponent(noteDto: NoteDto,context:Context) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Card(
            onClick = {
                Toast.makeText(context,"clicker",Toast.LENGTH_LONG).show()
                      println("you just clicked")
            },
            modifier = Modifier.height(120.dp).fillMaxWidth().padding(10.dp),
        ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.Center) {
                Text(
                    noteDto.tittle,
                    maxLines = 1,
                    style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.Black),
                    overflow = TextOverflow.Ellipsis
                )
                5.spaceHeight()
                Text(
                    noteDto.description,
                    maxLines = 1,
                    style = TextStyle(fontSize = 14.sp, fontStyle = FontStyle.Normal),
                    overflow = TextOverflow.Ellipsis
                )
                5.spaceHeight()
                Text(noteDto.getHumanReadableDate(), style = TextStyle(fontSize = 12.sp, fontStyle = FontStyle.Italic))
            }
        }
    }
}