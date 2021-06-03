package rs.raf.projekat2.luka_petrovic_rn3318.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import rs.raf.projekat2.luka_petrovic_rn3318.databinding.ActivityMainBinding
import rs.raf.projekat2.luka_petrovic_rn3318.ui.adapter.MainPagerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        initUi()
    }

    private fun initUi() {
        binding.mainViewPager.adapter =
            MainPagerAdapter(
                supportFragmentManager,
                this
            )
        binding.mainTabLayout.setupWithViewPager(binding.mainViewPager)
    }
}