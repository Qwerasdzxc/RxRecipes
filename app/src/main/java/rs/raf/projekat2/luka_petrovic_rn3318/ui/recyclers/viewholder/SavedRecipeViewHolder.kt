package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.viewholder

import androidx.recyclerview.widget.RecyclerView
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.LayoutItemSavedRecipeBinding
import java.text.SimpleDateFormat

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class SavedRecipeViewHolder(
    private val itemBinding: LayoutItemSavedRecipeBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(recipe: SavedRecipe) {
        itemBinding.savedRecipeItemTitle.text = recipe.title
        itemBinding.savedRecipeItemCategory.text = recipe.category
        itemBinding.savedRecipeItemDate.text = SimpleDateFormat("dd.MM.yyyy").format(recipe.date)
    }

}