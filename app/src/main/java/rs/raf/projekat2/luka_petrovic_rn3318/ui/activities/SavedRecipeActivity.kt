package rs.raf.projekat2.luka_petrovic_rn3318.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import rs.raf.projekat2.luka_petrovic_rn3318.R
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.ActivitySavedRecipeBinding
import java.io.File
import java.text.SimpleDateFormat

class SavedRecipeActivity : AppCompatActivity(R.layout.activity_saved_recipe) {

    private lateinit var binding: ActivitySavedRecipeBinding
    private lateinit var recipe: SavedRecipe

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySavedRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipe = intent.getParcelableExtra("recipe")!!
        init()
    }

    private fun init() {
        binding.savedRecipeTitle.text = recipe.title
        binding.savedRecipeDate.text = SimpleDateFormat("dd.MM.yyyy").format(recipe.date)
        binding.savedRecipeCategory.text = recipe.category
        binding.savedRecipePublisher.text = recipe.publisher
        binding.savedRecipeIngredients.setText(recipe.ingredients.joinToString(","))

        if (recipe.imagePath != null) {
            val file = File(recipe.imagePath!!)
            Glide.with(this).load(file).into(binding.savedRecipeImage)
        } else {
            Glide.with(this).load(recipe.image_url).into(binding.savedRecipeImage)
        }
    }
}