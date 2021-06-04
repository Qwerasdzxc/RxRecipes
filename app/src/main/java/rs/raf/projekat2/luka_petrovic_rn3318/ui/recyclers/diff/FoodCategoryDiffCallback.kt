package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.diff

import androidx.recyclerview.widget.DiffUtil
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.FoodCategory

/**
 * Created by Qwerasdzxc on 4.6.21..
 */
class FoodCategoryDiffCallback : DiffUtil.ItemCallback<FoodCategory>() {

    override fun areItemsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: FoodCategory, newItem: FoodCategory): Boolean {
        return oldItem.name == newItem.name && oldItem.imageUrl == newItem.imageUrl
    }

}