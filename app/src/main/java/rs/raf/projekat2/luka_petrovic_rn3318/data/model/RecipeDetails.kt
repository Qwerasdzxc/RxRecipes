package rs.raf.projekat2.luka_petrovic_rn3318.data.model

/**
 * Created by Qwerasdzxc on 4.6.21.
 */
data class RecipeDetails(
    val recipe_id: String,
    val title: String,
    val publisher: String,
    val image_url: String,
    val ingredients: List<String>
)