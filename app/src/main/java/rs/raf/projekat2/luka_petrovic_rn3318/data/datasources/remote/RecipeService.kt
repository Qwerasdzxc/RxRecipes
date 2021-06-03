package rs.raf.projekat2.luka_petrovic_rn3318.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeDetailsResponse
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeListResponse

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
interface RecipeService {

    @GET("v2/recipes")
    fun getAll(): Observable<RecipeListResponse>

    @GET("get")
    fun getById(@Query("rId") id: String): Observable<RecipeDetailsResponse>
}