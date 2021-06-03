package rs.raf.projekat2.luka_petrovic_rn3318.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeDetails
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Resource

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
interface RecipeRepository {

    fun fetchAll(): Observable<Resource<Unit>>
    fun getAll(): Observable<List<Recipe>>
    fun getAllByName(name: String): Observable<List<Recipe>>
    fun getById(id: String): Observable<RecipeDetails>
    fun saveRecipe(recipe: RecipeDetails) : Completable
    fun getSavedRecipes() : Observable<List<RecipeDetails>>
}