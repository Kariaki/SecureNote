package com.getontop.candidate.kariaki.securenote.core

import com.getontop.candidate.kariaki.securenote.data.local.repository.fake.FakeUploadStringData
import com.getontop.candidate.kariaki.securenote.exceptions.JsonFileFormatException
import org.junit.Assert.*

import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun `toNoteList test note fetching is successful`() {
        val fakeNoteJson = FakeUploadStringData.fakeUploadStringData
        val result = fakeNoteJson.toNoteList()
        assert(result.isNotEmpty())
    }
    @Test
    fun `toNoteList test note fetching failed and exception is thrown`() {
        val fakeNoteJson = FakeUploadStringData.fakeUploadStringData

        assertThrows(JsonFileFormatException::class.java){
            fakeNoteJson.toNoteList()
        }
    }
}