package rs.raf.projekat2.luka_petrovic_rn3318.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import rs.raf.projekat2.luka_petrovic_rn3318.R
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.RecipeDetailsContract
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.ActivityRecipeDetailsBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.RecipeDetailsState
import rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels.RecipeDetailsViewModel
import timber.log.Timber

class RecipeDetailsActivity : AppCompatActivity(R.layout.activity_recipe_details) {

    private val viewModel: RecipeDetailsContract.ViewModel by viewModel<RecipeDetailsViewModel>()
    private lateinit var binding: ActivityRecipeDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        val recipeId = intent.getStringExtra("recipe_id").toString()
        viewModel.getRecipeById(recipeId)

        viewModel.states.observe(this, {
            Timber.e(it.toString())
            renderState(it)
        })
    }

    private fun renderState(state: RecipeDetailsState) {
        when (state) {
            is RecipeDetailsState.Success -> {
                showLoadingState(false)
                val recipe = state.recipe
                Glide.with(this).load(recipe.image_url).into(binding.recipeDetailsImage)
                binding.recipeDetailsIngredients.setText(recipe.ingredients.joinToString(", "))
                binding.recipeDetailsTitle.text = recipe.title
                binding.recipeDetailsPublisher.text = recipe.publisher

                binding.recipeDetailsSaveRecipeButton.setOnClickListener {
                    viewModel.saveRecipe(state.recipe)
                    Toast.makeText(this, "Recipe saved", Toast.LENGTH_SHORT).show()
                }
            }
            is RecipeDetailsState.Error -> {
                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is RecipeDetailsState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.recipeDetailsImage.isVisible = !loading
        binding.recipeDetailsIngredients.isVisible = !loading
        binding.recipeDetailsPublisher.isVisible = !loading
        binding.recipeDetailsTitle.isVisible = !loading
        binding.recipeDetailsProgressBar.isVisible = loading
    }
}