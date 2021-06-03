package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.LayoutItemRecipeBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.diff.RecipeDiffCallback
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.viewholder.RecipeViewHolder

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class RecipeAdapter(
    private val glide: RequestManager,
    private val onItemClick: ((Recipe) -> Unit)
) : ListAdapter<Recipe, RecipeViewHolder>(RecipeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemBinding =
            LayoutItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(itemBinding, glide)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClick(getItem(position))
        }
    }

}