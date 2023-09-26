package com.getontop.candidate.kariaki.securenote.presentation.ui.routes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.getontop.candidate.kariaki.securenote.R
import com.getontop.candidate.kariaki.securenote.core.spaceWidth
import com.getontop.candidate.kariaki.securenote.presentation.ui.theme.Primary
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {},
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
        content = {
            it.calculateTopPadding()
            Column(modifier = androidx.compose.ui.Modifier.fillMaxWidth()) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = androidx.compose.ui.Modifier.fillMaxWidth()
                ) {
                    20.spaceWidth()
                    Text(
                        "Notes",
                        style = TextStyle(fontSize = 25.sp, fontWeight = FontWeight.Bold),
                        color = Color.Black
                    )
                    IconButton(
                        onClick = {},
                        content = {
                            Icon(
                                painter = painterResource(R.drawable.outline_cloud_download_24),
                                contentDescription = null,
                                modifier = androidx.compose.ui.Modifier.height(30.dp).width(30.dp)
                                    .padding(end = 5.dp)
                            )
                        })
                }
            }
        }, floatingActionButtonPosition = FabPosition.End
    )
}