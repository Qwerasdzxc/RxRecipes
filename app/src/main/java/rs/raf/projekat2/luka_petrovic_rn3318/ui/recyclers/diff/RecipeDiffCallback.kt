package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {

    override fun areItemsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Recipe, newItem: Recipe): Boolean {
        return oldItem.title == newItem.title
    }

}