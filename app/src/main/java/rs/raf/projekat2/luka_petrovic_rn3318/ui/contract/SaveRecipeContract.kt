package rs.raf.projekat2.luka_petrovic_rn3318.ui.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.SaveRecipeState

/**
 * Created by Qwerasdzxc on 5.6.21..
 */
interface SaveRecipeContract {

    interface ViewModel {

        val states: LiveData<SaveRecipeState>

        fun saveRecipe(recipe: SavedRecipe)
    }
}