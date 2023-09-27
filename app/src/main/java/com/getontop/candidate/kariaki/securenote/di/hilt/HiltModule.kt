package com.getontop.candidate.kariaki.securenote.di.hilt

import android.content.Context
import androidx.room.Room
import com.getontop.candidate.kariaki.securenote.data.local.database.AppRoomDatabase
import com.getontop.candidate.kariaki.securenote.data.local.database.dao.NoteDao
import com.getontop.candidate.kariaki.securenote.data.local.repository.fake.FakeNoteRepository
import com.getontop.candidate.kariaki.securenote.domain.repository.NoteRepository
import com.getontop.candidate.kariaki.securenote.domain.usecases.BaseUseCase
import com.getontop.candidate.kariaki.securenote.domain.usecases.DeleteNoteUseCase
import com.getontop.candidate.kariaki.securenote.domain.usecases.GetAllNotesUseCase
import com.getontop.candidate.kariaki.securenote.domain.usecases.InsertNoteUseCase
import com.getontop.candidate.kariaki.securenote.domain.usecases.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
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

    @Singleton
    @Provides
    fun providesNoteRepository(
        noteDao: NoteDao
    ):NoteRepository{
        return com.getontop.candidate.kariaki.securenote.data.local.repository.NoteRepositoryImpl(noteDao)
       // return FakeNoteRepository()
    }


    @Singleton
    @Provides
    fun provideBaseUseCase(repository: NoteRepository): BaseUseCase = BaseUseCase(
        DeleteNoteUseCase(repository),
        GetAllNotesUseCase(repository),
        InsertNoteUseCase(repository),
        UpdateNoteUseCase(repository)
    )
}
