package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.SavedRecipe
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.LayoutItemRecipeBinding
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.LayoutItemSavedRecipeBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.diff.RecipeDiffCallback
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.diff.SavedRecipeDiffCallback
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.viewholder.RecipeViewHolder
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.viewholder.SavedRecipeViewHolder

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class SavedRecipeAdapter(
    private val onItemClick: ((SavedRecipe) -> Unit)
) : ListAdapter<SavedRecipe, SavedRecipeViewHolder>(SavedRecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedRecipeViewHolder {
        val itemBinding =
            LayoutItemSavedRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedRecipeViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: SavedRecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClick(getItem(position))
        }
    }

}