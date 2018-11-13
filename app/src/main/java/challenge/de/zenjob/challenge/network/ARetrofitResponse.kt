package challenge.de.zenjob.challenge.network

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

    abstract fun onError(call: Call<T>, errorMsg: String)

    override fun onResponse(call: Call<T>, response: Response<T>) {

        if (!response.isSuccessful() || response.body() == null) {
            checkStatusCode(response.code())

            onError(call, "")
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
            "There was an error in connection"
        }
    }

    private fun checkStatusCode(statusCode: Int) {
        if (statusCode == 404) {
            "" //TODO
        } else if (statusCode == 401) {
            "" //login required
        }

    }

//    private fun isNetworkAvailable(): Boolean {
//        val connectivityManager = getSystemService( , Context.CONNECTIVITY_SERVICE)
//        return if (connectivityManager is ConnectivityManager) {
//            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
//            networkInfo?.isConnected ?: false
//        } else false
//    }
}

