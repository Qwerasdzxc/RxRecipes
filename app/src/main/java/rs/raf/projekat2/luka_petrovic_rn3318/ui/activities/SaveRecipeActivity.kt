package rs.raf.projekat2.luka_petrovic_rn3318.ui.activities

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.projekat2.luka_petrovic_rn3318.R
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.RecipeDetails
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.ActivitySaveRecipeBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.SaveRecipeContract
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.SaveRecipeState
import rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels.SaveRecipeViewModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class SaveRecipeActivity : AppCompatActivity(R.layout.activity_save_recipe) {

    private val viewModel: SaveRecipeContract.ViewModel by viewModel<SaveRecipeViewModel>()
    private lateinit var binding: ActivitySaveRecipeBinding

    private var selectedCategory: String = "Breakfast"
    private var selectedDate: Date = Calendar.getInstance().time

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySaveRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initObservers()
    }

    private fun initObservers() {
        val recipe = intent.getParcelableExtra<RecipeDetails>("recipe")!!
        binding.saveRecipeTitle.text = recipe.title
        binding.saveRecipeDate.text =
            SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        val cal = Calendar.getInstance()

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                binding.saveRecipeDate.text = sdf.format(cal.time)
                selectedDate = cal.time
            }

        binding.saveRecipeDate.setOnClickListener {
            DatePickerDialog(
                this@SaveRecipeActivity, dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }
        Glide.with(this).load(recipe.image_url).into(binding.saveRecipeImage)
        binding.saveRecipeCategorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    selectedCategory = when (position) {
                        1 -> "Lunch"
                        2 -> "Dinner"
                        else -> "Breakfast"
                    }
                }
            }
        binding.saveRecipeButton.setOnClickListener {
            viewModel.saveRecipe(
                SavedRecipe(
                    recipe.recipe_id,
                    recipe.title,
                    recipe.publisher,
                    selectedCategory,
                    recipe.image_url,
                    null,
                    selectedDate,
                    recipe.ingredients
                )
            )
        }


        viewModel.states.observe(this, {
            Timber.e(it.toString())
            renderState(it)
        })
    }

    private fun renderState(state: SaveRecipeState) {
        when (state) {
            is SaveRecipeState.Success -> {
                showLoadingState(false)
                Toast.makeText(this, "Recipe saved", Toast.LENGTH_SHORT).show()
            }
            is SaveRecipeState.Error -> {
                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is SaveRecipeState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.saveRecipeImage.isVisible = !loading
        binding.saveRecipeCategorySpinner.isVisible = !loading
        binding.saveRecipeDate.isVisible = !loading
        binding.saveRecipeTitle.isVisible = !loading
        binding.saveRecipeButton.isVisible = !loading
        binding.saveRecipeProgressBar.isVisible = loading
    }
}