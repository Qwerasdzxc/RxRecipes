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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.luka_petrovic_rn3318.R
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.FragmentRecipeListBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.activities.RecipeDetailsActivity
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.MainContract
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.adapter.FoodCategoryAdapter
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

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var categoryAdapter: FoodCategoryAdapter

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
        recipeAdapter = RecipeAdapter(Glide.with(this), onItemClick = {
            val intent = Intent(activity, RecipeDetailsActivity::class.java)
            intent.putExtra("recipe_id", it.id)
            startActivity(intent)
        })
        binding.recipeListRecyclerView.adapter = recipeAdapter

        binding.foodCategoryRecyclerView.layoutManager = LinearLayoutManager(context)
        categoryAdapter = FoodCategoryAdapter(Glide.with(this), onItemClick = {
            binding.recipeListSearchField.setText(it.name)
            mainViewModel.getRecipesByName(it.name)
        })
        binding.foodCategoryRecyclerView.adapter = categoryAdapter
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
        mainViewModel.getAllCategories()
    }

    private fun renderState(state: RecipesState) {
        when (state) {
            is RecipesState.RecipesLoaded -> {
                showLoadingState(false)
                binding.recipeListRecyclerView.isVisible = true
                binding.foodCategoryRecyclerView.isVisible = false
                recipeAdapter.submitList(state.recipes)
            }
            is RecipesState.Error -> {
                showLoadingState(false)
                binding.recipeListRecyclerView.isVisible = false
                binding.foodCategoryRecyclerView.isVisible = false
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is RecipesState.DataFetched -> {
                showLoadingState(false)
                binding.recipeListRecyclerView.isVisible = true
                binding.foodCategoryRecyclerView.isVisible = false
                Toast.makeText(context, "Fresh data fetched from the server", Toast.LENGTH_LONG)
                    .show()
            }
            is RecipesState.CategoriesLoaded -> {
                showLoadingState(false)
                binding.recipeListRecyclerView.isVisible = false
                binding.foodCategoryRecyclerView.isVisible = true
                categoryAdapter.submitList(state.categories)
            }
            is RecipesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.recipeListSearchField.isVisible = !loading
        binding.recipeListProgressBar.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}