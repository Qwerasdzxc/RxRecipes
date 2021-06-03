package rs.raf.projekat2.luka_petrovic_rn3318.ui.contract

import androidx.lifecycle.LiveData
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.RecipesState

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
interface MainContract {

    interface ViewModel {

        val recipesState: LiveData<RecipesState>

        fun fetchAllRecipes()
        fun getAllRecipes()
        fun getRecipesByName(name: String)
    }

}