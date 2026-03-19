package com.example.curso

//proyecto paginacion
interface Paginator <Key, Item>{
    suspend fun loadNextItems()
    fun reset()
}