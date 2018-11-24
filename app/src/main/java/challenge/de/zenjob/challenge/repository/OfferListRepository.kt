package challenge.de.zenjob.challenge.repository

import android.arch.lifecycle.MutableLiveData
import challenge.de.zenjob.challenge.network.ARetrofitResponseHandler
import challenge.de.zenjob.challenge.network.RetrofitApi
import challenge.de.zenjob.challenge.network.api.RestApi
import challenge.de.zenjob.challenge.repository.model.DataWraper
import challenge.de.zenjob.challenge.repository.model.OffersModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created on 2018-11-23, 3:31 PM.
 * @author Akram Shokri
 */

object OfferListRepository {

    val offerListResponse: MutableLiveData<DataWraper<OffersModel>> = MutableLiveData<DataWraper<OffersModel>>()

    fun fetchOffers(offset: String) : MutableLiveData<DataWraper<OffersModel>>{
        RetrofitApi()
            .create(RestApi::class.java)
            .fetchOffers(offset)
            .enqueue(object : ARetrofitResponseHandler<OffersModel>(), Callback<OffersModel> {
                override fun onSuccess(call: Call<OffersModel>, response: Response<OffersModel>) {
                    offerListResponse.postValue(DataWraper(response.body(), ""))
                }

                override fun onError(call: Call<OffersModel>, errorMsg: String?) {
                    offerListResponse.postValue(DataWraper(null, errorMsg))
                }

                override fun onFailure(call: Call<OffersModel>, t: Throwable, extra: String) {
                    offerListResponse.postValue(DataWraper(null, extra))
                }


            })

        return offerListResponse
    }
}