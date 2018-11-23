package challenge.de.zenjob.challenge.repository

/**
 * Created on 2018-11-16, 1:16 AM.
 * @author Akram Shokri
 */

import android.arch.lifecycle.MutableLiveData
import android.support.v7.app.AppCompatActivity
import challenge.de.zenjob.challenge.R
import challenge.de.zenjob.challenge.repository.model.LoginModel
import challenge.de.zenjob.challenge.repository.model.OfferModel
import challenge.de.zenjob.challenge.repository.model.OffersModel
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader

object DataRepository {

    var activity : AppCompatActivity? = null

    fun getOffers(): OffersModel{
        return loadOfferList()
    }

    fun getOffer(offerId : String) : OfferModel?{
        val offers = loadOfferList().offers
        return offers?.find {it.offerId.equals(offerId) }
    }

    fun totalJobCountSubHeader() : String {
        val total = loadOfferList().total
        return if (total > 1) {
            "" + total + " " + activity?.getString(R.string.job_sub_header)
        } else if (total == 1) {
             "" + total + " " + activity?.getString(R.string.job_sub_header_single)
         } else {
             " " + activity?.getString(R.string.job_sub_header_none)
         }
    }

    private fun loadLoginResponse() : LoginModel {
        val br = getReader("login-200-response.json")
        return Gson().fromJson(br, LoginModel::class.java)
    }

    private fun loadOfferList() : OffersModel {
        val br = getReader("offers-200-response.json")
        return Gson().fromJson(br, OffersModel::class.java)
    }

    private fun loadOffer() : OfferModel {
        val br = getReader("offer-200-response.json")
        return Gson().fromJson(br, OfferModel::class.java)
    }

    private fun getReader(jsonFile: String): BufferedReader {
        val inputStream = activity?.assets?.open(jsonFile)
        return BufferedReader( InputStreamReader (inputStream))
    }


}

