package rs.raf.projekat2.luka_petrovic_rn3318.ui.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import rs.raf.projekat2.luka_petrovic_rn3318.R
import rs.raf.projekat2.luka_petrovic_rn3318.ui.fragments.RecipeListFragment
import rs.raf.projekat2.luka_petrovic_rn3318.ui.fragments.SavedRecipesFragment

/**
 * Created by Qwerasdzxc on 3.6.21.
 */
class MainPagerAdapter(
    fragmentManager: FragmentManager,
    private val context: Context
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private const val ITEM_COUNT = 2
        const val FRAGMENT_1 = 0
    }

    override fun getItem(position: Int): Fragment {
        return when(position) {
            FRAGMENT_1 -> RecipeListFragment()
            else -> SavedRecipesFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when(position) {
            FRAGMENT_1 -> context.getString(R.string.recipes)
            else -> context.getString(R.string.saved_recipes)
        }
    }

}