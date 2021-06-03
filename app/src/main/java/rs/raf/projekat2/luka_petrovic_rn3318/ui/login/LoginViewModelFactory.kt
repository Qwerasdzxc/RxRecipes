package rs.raf.projekat2.luka_petrovic_rn3318.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rs.raf.projekat2.luka_petrovic_rn3318.data.LoginDataSource
import rs.raf.projekat2.luka_petrovic_rn3318.data.LoginRepository

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
class LoginViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}