package rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe
import rs.raf.projekat2.luka_petrovic_rn3318.data.repositories.RecipeRepository
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.SaveRecipeContract
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.SaveRecipeState
import timber.log.Timber

/**
 * Created by Qwerasdzxc on 5.6.21.
 */
class SaveRecipeViewModel(
    private val recipesRepository: RecipeRepository
) : ViewModel(), SaveRecipeContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val states: MutableLiveData<SaveRecipeState> = MutableLiveData()

    override fun saveRecipe(recipe: SavedRecipe) {
        val subscription = recipesRepository
            .saveRecipe(recipe)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    states.value = SaveRecipeState.Success(recipe)
                },
                {
                    states.value = SaveRecipeState.Error("Error happened while saving recipe")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}