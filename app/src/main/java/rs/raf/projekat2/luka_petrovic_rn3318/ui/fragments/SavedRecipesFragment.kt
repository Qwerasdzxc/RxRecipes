package rs.raf.projekat2.luka_petrovic_rn3318.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.FragmentSavedRecipesBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.activities.SavedRecipeActivity
import rs.raf.projekat2.luka_petrovic_rn3318.ui.contract.SavedRecipesContract
import rs.raf.projekat2.luka_petrovic_rn3318.ui.recyclers.adapter.SavedRecipeAdapter
import rs.raf.projekat2.luka_petrovic_rn3318.ui.view.states.SavedRecipesState
import rs.raf.projekat2.luka_petrovic_rn3318.ui.viewmodels.SavedRecipesViewModel
import timber.log.Timber


class SavedRecipesFragment : Fragment() {

    private val viewModel: SavedRecipesContract.ViewModel by sharedViewModel<SavedRecipesViewModel>()

    private var _binding: FragmentSavedRecipesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var recipeAdapter: SavedRecipeAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSavedRecipesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initUi()
        initObservers()
    }

    private fun initUi() {
        initRecycler()
    }

    private fun initRecycler() {
        binding.savedRecipesRecyclerView.layoutManager = LinearLayoutManager(context)
        recipeAdapter = SavedRecipeAdapter(onItemClick = {
            val intent = Intent(activity, SavedRecipeActivity::class.java)
            intent.putExtra("recipe", it)
            startActivity(intent)
        })
        binding.savedRecipesRecyclerView.adapter = recipeAdapter
    }

    private fun initObservers() {
        viewModel.states.observe(viewLifecycleOwner, {
            Timber.e(it.toString())
            renderState(it)
        })
        viewModel.loadRecipes()
    }

    private fun renderState(state: SavedRecipesState) {
        when (state) {
            is SavedRecipesState.Success -> {
                showLoadingState(false)
                binding.savedRecipesRecyclerView.isVisible = true
                recipeAdapter.submitList(state.recipes)
            }
            is SavedRecipesState.Error -> {
                showLoadingState(false)
                binding.savedRecipesRecyclerView.isVisible = false
                Toast.makeText(context, state.message, Toast.LENGTH_SHORT).show()
            }
            is SavedRecipesState.Loading -> {
                showLoadingState(true)
            }
        }
    }

    private fun showLoadingState(loading: Boolean) {
        binding.recipeListProgressBar.isVisible = loading
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}