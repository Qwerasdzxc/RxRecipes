package rs.raf.projekat2.luka_petrovic_rn3318.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val publisher: String,
    val imageUrl: String,
    val rank: Double
)