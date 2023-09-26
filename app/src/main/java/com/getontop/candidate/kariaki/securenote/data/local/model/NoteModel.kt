package com.getontop.candidate.kariaki.securenote.data.local.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
class NoteModel(
    val tittle: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val timestamp:Long,
    val createdAt:String,
) {
    constructor(
        tittle: String,
        description: String,
        timestamp: Long,
        createdAt: String
    ) : this(tittle, description,null,timestamp,createdAt)

}
