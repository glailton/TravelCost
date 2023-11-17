package glailton.io.github.travelcost.core.data

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Throwable) : Response<Nothing>()
}