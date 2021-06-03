package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.LayoutItemRecipeBinding

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class RecipeViewHolder(private val itemBinding: LayoutItemRecipeBinding, private val glide: RequestManager) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipe: Recipe) {
        glide.load(recipe.imageUrl).into(itemBinding.recipeItemImage)
        itemBinding.recipeItemName.text = recipe.title
        itemBinding.recipeItemPublisher.text = recipe.publisher
        itemBinding.recipeItemRank.text = recipe.rank.toInt().toString()
    }

}