package com.example.data.reporitories.books

import com.example.domain.entities.Volume
import com.example.domain.common.Result

interface BooksRemoteDataSource {
    suspend fun getBooks(author: String): Result<List<Volume>>
}