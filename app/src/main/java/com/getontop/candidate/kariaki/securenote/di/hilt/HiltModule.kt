package com.getontop.candidate.kariaki.securenote.di.hilt
import android.content.Context
import androidx.room.Room
import com.getontop.candidate.kariaki.securenote.data.local.database.AppRoomDatabase
import com.getontop.candidate.kariaki.securenote.data.local.database.dao.NoteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@InstallIn(SingletonComponent::class)
@Module
object HiltModule {
    @Singleton
    @Provides
    fun provideLocalDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppRoomDatabase::class.java,
        "database"
    )
        .fallbackToDestructiveMigration()
        .build()
    @Singleton
    @Provides
    fun provideNoteDao(database: AppRoomDatabase): NoteDao = database.noteDao()
}