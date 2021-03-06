package rs.raf.projekat2.luka_petrovic_rn3318.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeEntity
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipeEntity

/**
 * Created by Qwerasdzxc on 3.6.21..
 */
@Database(
    entities = [RecipeEntity::class, SavedRecipeEntity::class],
    version = 1,
    exportSchema = false)
@TypeConverters(StringListConverter::class, DateConverter::class)
abstract class RecipeDatabase : RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao
}