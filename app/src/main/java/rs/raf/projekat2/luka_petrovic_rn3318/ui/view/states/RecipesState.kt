package rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states

import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
sealed class RecipesState {
    object Loading: RecipesState()
    object DataFetched: RecipesState()
    data class Success(val recipes: List<Recipe>): RecipesState()
    data class Error(val message: String): RecipesState()
}