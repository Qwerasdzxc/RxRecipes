package rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states

import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe

/**
 * Created by Qwerasdzxc on 5.6.21..
 */
sealed class SavedRecipesState {
    object Loading: SavedRecipesState()
    data class Success(val recipes: List<SavedRecipe>): SavedRecipesState()
    data class Error(val message: String): SavedRecipesState()
}