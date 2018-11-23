package challenge.de.zenjob.challenge.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import challenge.de.zenjob.challenge.activity.LoginActivity
import challenge.de.zenjob.challenge.repository.LoginRepository
import challenge.de.zenjob.challenge.repository.model.DataWraper
import challenge.de.zenjob.challenge.repository.model.LoginModel

/**
 * Created on 2018-11-23, 1:38 PM.
 * @author Akram Shokri
 */
class LoginViewModel :ViewModel() {
    private val _loginData = LoginRepository.loginResponse

    val loginData : LiveData<DataWraper<LoginModel>>
        get() = _loginData

    fun attemptLogin(username: String, password: String){
        LoginRepository.authenticate(username, password)
    }
}