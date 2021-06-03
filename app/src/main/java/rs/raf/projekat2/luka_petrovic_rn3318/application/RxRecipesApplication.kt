package rs.raf.projekat2.luka_petrovic_rn3318.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.projekat2.luka_petrovic_rn3318.modules.coreModule
import rs.raf.projekat2.luka_petrovic_rn3318.modules.recipeModule
import timber.log.Timber

/**
 * Created by Qwerasdzxc on 3.6.21..
 */
class RxRecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            coreModule,
            recipeModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            // Use application context
            androidContext(this@RxRecipesApplication)
            // Use properties from assets/koin.properties
            androidFileProperties()
            // Use koin fragment factory for fragment instantiation
            fragmentFactory()
            // modules
            modules(modules)
        }
    }
}