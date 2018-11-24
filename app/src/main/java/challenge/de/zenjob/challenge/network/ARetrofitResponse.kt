package challenge.de.zenjob.challenge.network

import challenge.de.zenjob.challenge.repository.model.Error
import challenge.de.zenjob.challenge.repository.model.LoginModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * Created on 2018-11-11, 12:17 AM.
 * @author Akram Shokri
 */

 abstract class ARetrofitResponseHandler<T> : Callback<T> {

    abstract fun onSuccess(call: Call<T>, response: Response<T>)

    abstract fun onFailure(call: Call<T>, t: Throwable, extra: String)

    abstract fun onError(call: Call<T>, errorMsg: String?)

    override fun onResponse(call: Call<T>, response: Response<T>) {

        if (!response.isSuccessful || response.body() == null) {
            if( response.errorBody() != null) {
                val gson = Gson()
                val type = object : TypeToken<LoginModel>() {}.type
                var errorResponse: LoginModel = gson.fromJson(response.errorBody()!!.charStream(), type)

                onError(call,  errorResponse.error?.message)
            } else{
                onError(call, checkStatusCode(response.code()))
            }
            return
        }

        onSuccess(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure(call, t, messageForException(t))
    }

    private fun messageForException(t: Throwable): String {

        return if (t is IOException) {
            "Problem in contacting server. Please check your internet connectivity and try again."
        } else {
            "There was an error in connection. Please try again later."
        }
    }

    private fun checkStatusCode(statusCode: Int) : String{
        return when (statusCode) {
            404 -> "The API address has changed."
            401 -> "Your session has expired, please login to continue"
            else -> "There was a problem in contacting server"
        }

    }

}

