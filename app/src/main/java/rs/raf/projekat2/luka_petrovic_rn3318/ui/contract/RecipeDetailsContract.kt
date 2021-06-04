package rs.raf.projekat2.luka_petrovic_rn3318.ui.contract

import androidx.lifecycle.LiveData
import io.reactivex.Completable
import io.reactivex.Observable
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeDetails
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.RecipeDetailsState

/**
 * Created by Qwerasdzxc on 4.6.21..
 */
interface RecipeDetailsContract {

    interface ViewModel {

        val states: LiveData<RecipeDetailsState>

        fun getRecipeById(id: String)
    }
}