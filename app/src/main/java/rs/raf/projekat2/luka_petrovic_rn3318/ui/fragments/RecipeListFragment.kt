package rs.raf.projekat2.luka_petrovic_rn3318.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.luka_petrovic_rn3318.R
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.FragmentRecipeListBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.activities.RecipeDetailsActivity
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.MainContract
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.adapter.RecipeAdapter
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.RecipesState
import rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels.MainViewModel
import timber.log.Timber

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {

    // Koristimo by sharedViewModel jer sada view modele instanciramo kroz koin
    private val mainViewModel: MainContract.ViewModel by sharedViewModel<MainViewModel>()

    private var _binding: FragmentRecipeListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
        initListeners()
    }

    private fun initRecycler() {
        binding.recipeListRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = RecipeAdapter(Glide.with(this), onItemClick = {
            val intent = Intent(activity, RecipeDetailsActivity::class.java)
            intent.putExtra("recipe_id", it.id)
            startActivity(intent)
        })
        binding.recipeListRecyclerView.adapter = adapter
    }

    private fun initListeners() {
        binding.recipeListSearchField.doAfterTextChanged {
            val filter = it.toString()
            mainViewModel.getRecipesByName(filter)
        }
    }

    private fun initObservers() {
        mainViewModel.recipesState.observe(viewLifecycleOwner, {
            Timber.e(it.toString())
            renderState(it)
        })
        // Pravimo subscription kad observablu koji je vezan za getAll iz baze
        // Na svaku promenu tabele, obserrvable ce emitovati na onNext sve elemente
        // koji zadovoljavaju query
        mainViewModel.getAllRecipes()
        // Pokrecemo operaciju dovlacenja podataka sa servera, kada podaci stignu,
        // bice sacuvani u bazi, tada ce se triggerovati observable na koji smo se pretplatili
        // preko metode getAllMovies()
        mainViewModel.fetchAllRecipes()
    }

    private fun renderState(state: RecipesState) {
        when (state) {
            is RecipesState.Success -> {
                showLoadingState(false)
                adapter.submitList(state.recipes)
            }
            is RecipesState.Error -> {
                showLoadingState(false)
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is RecipesState.DataFetched -> {
                showLoadingState(false)
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG)
                    .show()
            }
            is RecipesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.recipeListSearchField.isVisible = !loading
        binding.recipeListRecyclerView.isVisible = !loading
        binding.recipeListProgressBar.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}