package rs.raf.projekat2.luka_petrovic_rn3318.data.model

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<out T>(val message: String = "") : Resource<T>()
    data class Error<out T>(val error: Throwable = Throwable(), val data: T? = null): Resource<T>()
}