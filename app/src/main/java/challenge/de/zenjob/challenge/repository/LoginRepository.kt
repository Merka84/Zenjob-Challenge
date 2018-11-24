package challenge.de.zenjob.challenge.repository

import android.arch.lifecycle.MutableLiveData
import challenge.de.zenjob.challenge.network.ARetrofitResponseHandler
import challenge.de.zenjob.challenge.network.RetrofitApi
import challenge.de.zenjob.challenge.network.api.RestApi
import challenge.de.zenjob.challenge.repository.model.DataWraper
import challenge.de.zenjob.challenge.repository.model.LoginModel
import challenge.de.zenjob.challenge.repository.model.LoginParam
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created on 2018-11-23, 11:08 AM.
 * @author Akram Shokri
 */
object LoginRepository {
    val loginResponse : MutableLiveData<DataWraper<LoginModel>> = MutableLiveData<DataWraper<LoginModel>>()


    fun authenticate (username: String, password: String): MutableLiveData<DataWraper<LoginModel>>{

        RetrofitApi()
            .create(RestApi::class.java, false)
            .login(LoginParam(username , password))
            .enqueue(object : ARetrofitResponseHandler<LoginModel>(), Callback<LoginModel> {
                override fun onSuccess(call: Call<LoginModel>, response: Response<LoginModel>) {
                    loginResponse.postValue(DataWraper(response.body(), ""))
                }

                override fun onError(call: Call<LoginModel>, errorMsg: String?) {
                    loginResponse.postValue(DataWraper(null, errorMsg))
                }

                override fun onFailure(call: Call<LoginModel>, t: Throwable, extra: String) {
                    loginResponse.postValue(DataWraper(null, extra))
                }


            })

        return loginResponse

    }
}