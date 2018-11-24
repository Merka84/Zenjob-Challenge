package challenge.de.zenjob.challenge.repository

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import challenge.de.zenjob.challenge.network.ARetrofitResponseHandler
import challenge.de.zenjob.challenge.network.RetrofitApi
import challenge.de.zenjob.challenge.network.api.RestApi
import challenge.de.zenjob.challenge.repository.model.DataWraper
import challenge.de.zenjob.challenge.repository.model.OfferModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created on 2018-11-23, 5:33 PM.
 * @author Akram Shokri
 */

object OfferRepository {

    val offerResponse: MutableLiveData<OfferModel> = MutableLiveData<OfferModel>()

    fun fetchOfferById(offerId: String) : MutableLiveData<OfferModel> {
        RetrofitApi()
            .create(RestApi::class.java)
            .fetchOfferById(offerId)
            .enqueue(object : ARetrofitResponseHandler<OfferModel>(), Callback<OfferModel> {
                override fun onSuccess(call: Call<OfferModel>, response: Response<OfferModel>) {
                    offerResponse.postValue(response.body())
                }

                override fun onError(call: Call<OfferModel>, errorMsg: String?) {
                    //offerResponse.postValue()
                    Log.d("", errorMsg)
                }

                override fun onFailure(call: Call<OfferModel>, t: Throwable, extra: String) {
                    //offerResponse.postValue(DataWraper(null, extra))
                    Log.d("", extra)
                }


            })

        return offerResponse
    }
}