package com.getontop.candidate.kariaki.securenote.core

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import com.getontop.candidate.kariaki.securenote.domain.dto.InsertNoteDto
import com.getontop.candidate.kariaki.securenote.domain.dto.NoteDto
import com.getontop.candidate.kariaki.securenote.exceptions.JsonFileFormatException
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

fun NoteModel.toNoteDto(): NoteDto =
    NoteDto(this.tittle, this.description, this.id!!, this.timestamp)
fun InsertNoteDto.toNoteModel():NoteModel{
    val now = System.currentTimeMillis()
    return NoteModel(this.title, this.description, timestamp =  now)
}

fun String.toNoteList(): Array<InsertNoteDto> {
    try {
       return Gson()
            .fromJson(this, Array<InsertNoteDto>::class.java)
            ?: throw Exception("Unable to convert data")
    }catch (e:Exception){
        throw JsonFileFormatException()
    }
}
 fun Uri.extractString(context: Context): String {
    var reader1: BufferedReader? = null
    try {
        val `in`: InputStream? =
            context.contentResolver.openInputStream(this)
        reader1 = BufferedReader(InputStreamReader(`in`))
        var line: String?
        val builder = StringBuilder()
        while (reader1.readLine().also { line = it } != null) {
            builder.append(line)
        }
        return builder.toString()
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        if (reader1 != null) {
            try {
                reader1.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    return ""

}
@Composable
fun Int.spaceHeight(){
    Spacer(modifier = Modifier.height(this.dp))
}
@Composable
fun Int.spaceWidth(){
    Spacer(modifier = Modifier.width(this.dp))
}