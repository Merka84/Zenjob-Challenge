package challenge.de.zenjob.challenge

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import challenge.de.zenjob.challenge.repository.LoginRepository
import challenge.de.zenjob.challenge.repository.model.DataWraper
import challenge.de.zenjob.challenge.repository.model.LoginModel
import challenge.de.zenjob.challenge.viewmodel.LoginViewModel
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify

/**
 * Created on 2018-11-24, 2:40 PM.
 * @author Akram Shokri
 */
class LoginViewModelTest{
    @Mock
    lateinit var observer: Observer<DataWraper<LoginModel>>

    val loginRepository = Mockito.mock(LoginRepository::class.java)

    var loginVM: LoginViewModel? = null

    @Test
    fun testLogin() {
        loginVM = LoginViewModel()
        loginVM?.loginData?.observeForever(observer)
        loginVM?.attemptLogin("aaa@bb.cc", "1223")
       whenever(loginRepository
            .authenticate(anyString(), anyString())).thenReturn(MutableLiveData<DataWraper<LoginModel>>())

        verify(observer).onChanged(loginVM?.loginData?.value)
    }
}