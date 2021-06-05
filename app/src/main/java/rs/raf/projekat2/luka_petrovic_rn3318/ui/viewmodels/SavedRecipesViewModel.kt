package rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.projekat2.luka_petrovic_rn3318.data.repositories.RecipeRepository
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.SavedRecipesContract
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.SavedRecipesState
import timber.log.Timber

/**
 * Created by Qwerasdzxc on 5.6.21.
 */
class SavedRecipesViewModel(
    private val recipesRepository: RecipeRepository
) : ViewModel(), SavedRecipesContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val states: MutableLiveData<SavedRecipesState> = MutableLiveData()

    override fun loadRecipes() {
        val subscription = recipesRepository
            .getSavedRecipes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    states.value = SavedRecipesState.Success(it)
                },
                {
                    states.value = SavedRecipesState.Error("Error happened while fetching data")
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