package rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.RequestManager
import rs.raf.projekat2.luka_petrovic_rn3318.data.model.FoodCategory
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.LayoutItemFoodCategoryBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.diff.FoodCategoryDiffCallback
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.viewholder.FoodCategoryViewHolder

/**
 * Created by Qwerasdzxc on 4.6.21..
 */
class FoodCategoryAdapter(
    private val glide: RequestManager,
    private val onItemClick: ((FoodCategory) -> Unit)
) : ListAdapter<FoodCategory, FoodCategoryViewHolder>(FoodCategoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryViewHolder {
        val itemBinding =
            LayoutItemFoodCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodCategoryViewHolder(itemBinding, glide)
    }

    override fun onBindViewHolder(holder: FoodCategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnClickListener {
            onItemClick(getItem(position))
        }
    }

}