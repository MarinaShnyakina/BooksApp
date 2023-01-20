package com.example.domain.repositories

import com.example.domain.entities.Volume
import kotlinx.coroutines.flow.Flow
import com.example.domain.common.Result


interface BooksRepository {

    suspend fun getRemoteBooks(author: String): Result<List<Volume>>

    suspend fun getBookmarks(): Flow<List<Volume>>

    suspend fun bookmark(book: Volume)

    suspend fun unbookmark(book: Volume)
}