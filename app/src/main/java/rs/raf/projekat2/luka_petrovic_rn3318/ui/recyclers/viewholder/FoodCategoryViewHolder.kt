package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.FoodCategory
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.Recipe
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.LayoutItemFoodCategoryBinding

/**
 * Created by Qwerasdzxc on 4.6.21..
 */
class FoodCategoryViewHolder(
    private val itemBinding: LayoutItemFoodCategoryBinding,
    private val glide: RequestManager
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(category: FoodCategory) {
        glide.load(category.imageUrl).into(itemBinding.foodCategoryItemImage)
        itemBinding.foodCategoryItemName.text = category.name
    }

}