package com.example.curso

import android.graphics.pdf.models.ListItem
import androidx.compose.material3.ListItem
import kotlinx.coroutines.delay

data class MyItem(
    val title: String,
    val description: String
)

class Repository {

    // proyecto paginacion

    private val remoteDataSource = (1..100).map {
        MyItem(
            title = "Item $it",
            description = "Description $it"
        )
    }

    suspend fun getItems(page: Int, pageSize: Int): Result<List<MyItem>> {
        delay(2000L) // Simula latencia de red

        val startingIndex = page * pageSize

        // 3. Verificamos que el índice no se pase del tamaño de la lista
        return if (startingIndex < remoteDataSource.size) {
            val endIndex = minOf(startingIndex + pageSize, remoteDataSource.size)
            Result.success(
                remoteDataSource.slice(startingIndex until endIndex)
            )
        } else {
            Result.success(emptyList())
        }
    }
}