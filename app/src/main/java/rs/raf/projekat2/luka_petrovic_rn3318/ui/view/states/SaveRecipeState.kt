package rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states

import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe

/**
 * Created by Qwerasdzxc on 5.6.21.
 */
sealed class SaveRecipeState {
    object Loading: SaveRecipeState()
    data class Success(val recipe: SavedRecipe): SaveRecipeState()
    data class Error(val message: String): SaveRecipeState()
}