package rs.raf.projekat2.luka_petrovic_rn3318.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by Qwerasdzxc on 4.6.21.
 */
@Entity(tableName = "saved_recipes")
data class SavedRecipeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val publisher: String,
    val category: String,
    val imageUrl: String,
    val imagePath: String?,
    val date: Date,
    val ingredients: List<String>
)