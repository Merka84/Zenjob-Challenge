package challenge.de.zenjob.challenge.network

/**
 * Created on 2018-11-11, 12:43 AM.
 * @author Akram Shokri
 */


import challenge.de.zenjob.challenge.TokenManager
import challenge.de.zenjob.challenge.repository.model.LoginModel
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Akram Shokri on 6/30/2018.
 */
class RetrofitApi{
    val BASE_URL = "https://staging-main.zenjob.org"
    val TIME_OUT = 100 //in second

    private var okHttpBuilder: OkHttpClient.Builder? = null
    private var retrofit: Retrofit? = null

    fun <T> create(clazz: Class<T>): T {
        initOkHttp(true)
        initRetrofit()
        return retrofit!!.create(clazz)
    }

    fun <T> create(clazz: Class<T>, withAuth : Boolean): T {
        initOkHttp(withAuth)
        initRetrofit()
        return retrofit!!.create(clazz)
    }


    private fun initOkHttp(withAuth : Boolean) {
        okHttpBuilder = OkHttpClient().newBuilder()
        okHttpBuilder!!.readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        okHttpBuilder!!.connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
        okHttpBuilder!!.addInterceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
                .addHeader("Content-Type", "application/json")

            if (withAuth && TokenManager.token() != null) {
                requestBuilder.addHeader(
                    "Authorization",
                    TokenManager.tokenType()
                            + " "
                            + TokenManager.token()
                )
            }
            chain.proceed(requestBuilder.build())
        }
    }


    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpBuilder!!.build())
            .build()
    }

}