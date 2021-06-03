package rs.raf.projekat2.luka_petrovic_rn3318.data.repositories

import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.luka_petrovic_rn3318.data.datasources.local.RecipeDao
import rs.raf.projekat2.luka_petrovic_rn3318.data.datasources.remote.RecipeService
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.*
import timber.log.Timber

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class RecipeRepositoryImpl(
    private val localDataSource: RecipeDao,
    private val remoteDataSource: RecipeService
) : RecipeRepository {

    override fun fetchAll(): Observable<Resource<Unit>> {
        return remoteDataSource
            .getAll()
            .doOnNext { it ->
                Timber.e("Upis u bazu")
                val entities = it.recipes.map { recipe ->
                    RecipeEntity(
                        recipe.id,
                        recipe.title,
                        recipe.publisher,
                        recipe.imageUrl,
                        recipe.rank
                    )
                }
                localDataSource.deleteAndInsertAll(entities)
            }
            .map {
                Resource.Success(Unit)
            }
        // Kada zelimo sami da kontrolisemo sta se desava sa greskom, umesto da je samo prepustimo
        // error handleru, mozemo iskoristiti operator onErrorReturn, tada sami na osnovu greske
        // odlucujemo koju vrednost cemo da vratimo. Ta vrednost mora biti u skladu sa povratnom
        // vrednoscu lanca.
        // Kada se iskoristi onErrorReturn onError lambda u viewmodelu nece biti izvrsena,
        // nego ce umesdto nje biti izvsena onNext koja ce kao parametar primiti vrednost koja
        // je vracena iz onErrorReturn
        // Obicno se koristi kada je potrebno proveriti koji kod greske je vratio server.
//            .onErrorReturn {
//                when(it) {
//                    is HttpException -> {
//                        when(it.code()) {
//                            in 400..499 -> {
//
//                            }
//                        }
//                    }
//                }
//                Timber.e("ON ERROR RETURN")
//            }
    }

    override fun getAll(): Observable<List<Recipe>> {
        return localDataSource
            .getAll()
            .map { it ->
                it.map {
                    Recipe(it.id, it.title, it.publisher, it.imageUrl, it.rank)
                }
            }
    }

    override fun getAllByName(name: String): Observable<List<Recipe>> {
        return localDataSource
            .getByName(name)
            .map { it ->
                it.map {
                    Recipe(it.id, it.title, it.publisher, it.imageUrl, it.rank)
                }
            }
    }

    override fun getById(id: String): Observable<RecipeDetails> {
        return remoteDataSource
            .getById(id)
            .map {
                RecipeDetails(
                    it.recipe.recipe_id,
                    it.recipe.title,
                    it.recipe.publisher,
                    it.recipe.image_url,
                    it.recipe.ingredients
                )
            }
    }

    override fun saveRecipe(recipe: RecipeDetails) : Completable {
        return localDataSource.saveRecipe(
            RecipeDetailsEntity(
                recipe.recipe_id,
                recipe.title,
                recipe.publisher,
                recipe.image_url,
                recipe.ingredients
            )
        )
    }

    override fun getSavedRecipes(): Observable<List<RecipeDetails>> {
        return localDataSource
            .getSavedRecipes()
            .map { it ->
                it.map {
                    RecipeDetails(it.id, it.title, it.publisher, it.imageUrl, it.ingredients)
                }
            }
    }
}