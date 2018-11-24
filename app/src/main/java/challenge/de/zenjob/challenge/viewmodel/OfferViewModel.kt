package challenge.de.zenjob.challenge.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import challenge.de.zenjob.challenge.repository.OfferListRepository
import challenge.de.zenjob.challenge.repository.OfferRepository
import challenge.de.zenjob.challenge.repository.model.DataWraper
import challenge.de.zenjob.challenge.repository.model.OfferModel
import challenge.de.zenjob.challenge.repository.model.OffersModel

/**
 * Created on 2018-11-23, 3:52 PM.
 * @author Akram Shokri
 */
class OfferViewModel : ViewModel() {
    private val _offerData = OfferRepository.offerResponse

    val offer : LiveData<OfferModel>
        get() = _offerData

    fun getOfferById(offerId: String){
        OfferRepository.fetchOfferById(offerId)
    }
}