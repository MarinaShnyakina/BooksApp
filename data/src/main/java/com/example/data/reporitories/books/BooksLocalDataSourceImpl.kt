package com.example.data.reporitories.books

import com.example.data.db.BookDao
import com.example.data.mappers.BookEntityMapper
import com.example.domain.entities.Volume
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class BooksLocalDataSourceImpl(
    private val bookDao: BookDao,
    private val dispatcher: CoroutineDispatcher,
    private val bookEntityMapper: BookEntityMapper
) : BooksLocalDataSource {
    override suspend fun bookmark(book: Volume) = withContext(dispatcher) {
        bookDao.saveBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun unbookmark(book: Volume) = withContext(dispatcher) {
        bookDao.deleteBook(bookEntityMapper.toBookEntity(book))
    }

    override suspend fun getBookmarks(): Flow<List<Volume>> {
        val savedBooksFlow = bookDao.getSavedBooks()
        return savedBooksFlow.map { list ->
            list.map { element ->
                bookEntityMapper.toVolume(element)
            }
        }
    }
}