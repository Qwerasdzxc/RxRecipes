package rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeDetails
import rs.raf.projekat2.luka_petrovic_rn3318.data.repositories.RecipeRepository
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.RecipeDetailsContract
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.RecipeDetailsState
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.RecipesState
import timber.log.Timber

/**
 * Created by Qwerasdzxc on 4.6.21..
 */
class RecipeDetailsViewModel(
    private val recipesRepository: RecipeRepository
) : ViewModel(), RecipeDetailsContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val states: MutableLiveData<RecipeDetailsState> = MutableLiveData()

    override fun getRecipeById(id: String) {
        val subscription = recipesRepository
            .getById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    states.value = RecipeDetailsState.Success(it)
                },
                {
                    states.value = RecipeDetailsState.Error("Error happened while fetching data")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun saveRecipe(recipe: RecipeDetails) {
        val subscription = recipesRepository
            .saveRecipe(recipe)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    states.value = RecipeDetailsState.Success(recipe)
                },
                {
                    states.value = RecipeDetailsState.Error("Error happened while saving recipe")
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