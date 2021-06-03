package rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states

import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeDetails

/**
 * Created by Qwerasdzxc on 4.6.21..
 */
sealed class RecipeDetailsState {
    object Loading: RecipeDetailsState()
    data class Success(val recipe: RecipeDetails): RecipeDetailsState()
    data class Error(val message: String): RecipeDetailsState()
}
