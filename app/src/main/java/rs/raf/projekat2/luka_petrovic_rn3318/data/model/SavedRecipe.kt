package rs.raf.projekat2.luka_petrovic_rn3318.data.model

import java.util.*

/**
 * Created by Qwerasdzxc on 4.6.21.
 */
data class SavedRecipe(
    val recipe_id: String,
    val title: String,
    val publisher: String,
    val category: String,
    val image_url: String,
    val imagePath: String?,
    val date: Date,
    val ingredients: List<String>
)