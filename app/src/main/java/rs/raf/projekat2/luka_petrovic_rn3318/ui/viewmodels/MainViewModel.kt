package rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Resource
import rs.raf.projekat2.luka_petrovic_rn3318.data.repositories.RecipeRepository
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.MainContract
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.RecipesState
import timber.log.Timber
import java.util.concurrent.TimeUnit

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class MainViewModel(
    private val recipesRepository: RecipeRepository
) : ViewModel(), MainContract.ViewModel {

    private val subscriptions = CompositeDisposable()
    override val recipesState: MutableLiveData<RecipesState> = MutableLiveData()

    private val publishSubject: PublishSubject<String> = PublishSubject.create()

    init {
        val subscription = publishSubject
            .debounce(200, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .switchMap { it ->
                recipesRepository
                    .getAllByName(it)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnError {
                        Timber.e("Error in publish subject")
                        Timber.e(it)
                    }
            }
            .subscribe(
                {
                    recipesState.value = RecipesState.Success(it)
                },
                {
                    recipesState.value = RecipesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun fetchAllRecipes() {
        val subscription = recipesRepository
            .fetchAll()
            .startWith(Resource.Loading()) //Kada se pokrene fetch hocemo da postavimo stanje na Loading
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when(it) {
                        is Resource.Loading -> recipesState.value = RecipesState.Loading
                        is Resource.Success -> recipesState.value = RecipesState.DataFetched
                        is Resource.Error -> recipesState.value = RecipesState.Error("Error happened while fetching data from the server")
                    }
                },
                {
                    recipesState.value = RecipesState.Error("Error happened while fetching data from the server")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getAllRecipes() {
        val subscription = recipesRepository
            .getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    recipesState.value = RecipesState.Success(it)
                },
                {
                    recipesState.value = RecipesState.Error("Error happened while fetching data from db")
                    Timber.e(it)
                }
            )
        subscriptions.add(subscription)
    }

    override fun getRecipesByName(name: String) {
        publishSubject.onNext(name)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}