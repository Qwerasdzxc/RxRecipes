package rs.raf.projekat2.luka_petrovic_rn3318.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.projekat2.luka_petrovic_rn3318.data.datasources.local.RecipeDatabase
import rs.raf.projekat2.luka_petrovic_rn3318.data.datasources.remote.RecipeService
import rs.raf.projekat2.luka_petrovic_rn3318.data.repositories.RecipeRepository
import rs.raf.projekat2.luka_petrovic_rn3318.data.repositories.RecipeRepositoryImpl
import rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels.MainViewModel
import rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels.RecipeDetailsViewModel

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
val recipeModule = module {

    viewModel { MainViewModel(recipesRepository = get()) }
    viewModel { RecipeDetailsViewModel(recipesRepository = get()) }

    single<RecipeRepository> { RecipeRepositoryImpl(localDataSource = get(), remoteDataSource = get()) }

    single { get<RecipeDatabase>().getRecipeDao() }

    single<RecipeService> { create(retrofit = get()) }
}