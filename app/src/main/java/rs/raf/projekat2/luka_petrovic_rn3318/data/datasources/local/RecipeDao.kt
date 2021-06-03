package rs.raf.projekat2.luka_petrovic_rn3318.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeDetailsEntity
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeEntity

/**
 * Created by Qwerasdzxc on 3.6.21..
 */
@Dao
abstract class RecipeDao {
    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insert(entity: RecipeEntity): Completable

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun insertAll(entities: List<RecipeEntity>): Completable

    @Query("SELECT * FROM recipes")
    abstract fun getAll(): Observable<List<RecipeEntity>>

    @Query("DELETE FROM recipes")
    abstract fun deleteAll()

    @Transaction
    open fun deleteAndInsertAll(entities: List<RecipeEntity>) {
        deleteAll()
        insertAll(entities).blockingAwait()
    }

    @Query("SELECT * FROM recipes WHERE title LIKE :name || '%'")
    abstract fun getByName(name: String): Observable<List<RecipeEntity>>

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    abstract fun saveRecipe(entity: RecipeDetailsEntity): Completable

    @Query("SELECT * FROM recipe_details")
    abstract fun getSavedRecipes(): Observable<List<RecipeDetailsEntity>>
}