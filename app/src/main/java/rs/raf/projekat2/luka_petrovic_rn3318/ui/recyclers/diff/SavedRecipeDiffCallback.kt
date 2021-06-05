package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class SavedRecipeDiffCallback : DiffUtil.ItemCallback<SavedRecipe>() {

    override fun areItemsTheSame(oldItem: SavedRecipe, newItem: SavedRecipe): Boolean {
        return oldItem.recipe_id == newItem.recipe_id
    }

    override fun areContentsTheSame(oldItem: SavedRecipe, newItem: SavedRecipe): Boolean {
        return oldItem.title == newItem.title
    }

}