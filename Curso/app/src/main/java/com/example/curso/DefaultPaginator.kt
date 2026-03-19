package com.example.curso;

import javax.xml.transform.Result;

class DefaultPaginator<Key, Item>(
        //deberian llevar inline pero da error
        private val initialKey: Key,
        private val onLoadUpdated: (Boolean) -> Unit,
        private val onRequest: suspend (nextKey: Key) -> kotlin.Result<List<Item>>,
        private val getNextKey: suspend (List<Item>) -> Key,
        private val onError: suspend(Throwable?) -> Unit,
        private val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

        // Proyecto paginación

        private var currentKey: Key? = initialKey
        private var isMakingRequest = false

        override fun reset() {
                currentKey = initialKey
        }

        override suspend fun loadNextItems() {
              if(isMakingRequest){
                      return
              }
                isMakingRequest = true
                onLoadUpdated(true)
                //val result = onRequest(currentKey)
                isMakingRequest = false
//                val items = result.getOrElse {
//                        onError(it)
//                        onLoadUpdated(false)
//                        return
//                }
                //currentKey = getNextKey(items)
                //onSuccess(items, currentKey)
                onLoadUpdated(false)
        }
}
