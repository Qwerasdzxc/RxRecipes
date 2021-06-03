package rs.raf.projekat2.luka_petrovic_rn3318.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Qwerasdzxc on 4.6.21.
 */
@Entity(tableName = "recipe_details")
data class RecipeDetailsEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val ingredients: List<String>
)