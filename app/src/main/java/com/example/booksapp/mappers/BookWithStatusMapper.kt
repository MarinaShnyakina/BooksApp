package com.example.booksapp.mappers

import com.example.booksapp.entities.BookWithStatus
import com.example.booksapp.entities.BookmarkStatus
import com.example.domain.entities.Volume
import com.example.domain.entities.VolumeInfo

class BookWithStatusMapper {
    fun fromVolumeToBookWithStatus(
        volumes: List<Volume>,
        bookmarks: List<Volume>
    ): List<BookWithStatus> {
        val commonElements = volumes.filter { it.id in bookmarks.map { bookmark -> bookmark.id } }
        val booksWithStatus = arrayListOf<BookWithStatus>()
        for (volume in volumes) {
            if (volume in commonElements) {
                booksWithStatus.add(
                    BookWithStatus(
                        volume.id,
                        volume.volumeInfo.title,
                        volume.volumeInfo.authors,
                        volume.volumeInfo.imageUrl,
                        BookmarkStatus.BOOKMARKED
                    )
                )
            } else {
                booksWithStatus.add(
                    BookWithStatus(
                        volume.id,
                        volume.volumeInfo.title,
                        volume.volumeInfo.authors,
                        volume.volumeInfo.imageUrl,
                        BookmarkStatus.UNBOOKMARKED
                    )
                )
            }
        }
        return booksWithStatus.sortedBy { it.id }
    }

    fun fromBookWithStatusToVolume(bookWithStatus: BookWithStatus): Volume {
        return Volume(
            bookWithStatus.id,
            VolumeInfo(bookWithStatus.title, bookWithStatus.authors, bookWithStatus.imageUrl)
        )
    }
}