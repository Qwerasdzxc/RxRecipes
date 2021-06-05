package rs.raf.projekat2.luka_petrovic_rn3318.ui.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.SavedRecipesState

/**
 * Created by Qwerasdzxc on 5.6.21..
 */
interface SavedRecipesContract {

    interface ViewModel {

        val states: LiveData<SavedRecipesState>

        fun loadRecipes()
    }
}