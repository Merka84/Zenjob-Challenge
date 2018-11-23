package challenge.de.zenjob.challenge.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import challenge.de.zenjob.challenge.repository.OfferListRepository
import challenge.de.zenjob.challenge.repository.model.DataWraper
import challenge.de.zenjob.challenge.repository.model.OffersModel

/**
 * Created on 2018-11-23, 3:52 PM.
 * @author Akram Shokri
 */
class OfferListViewModel : ViewModel() {
    private val _offersData = OfferListRepository.offerListResponse

    val offersData : LiveData<DataWraper<OffersModel>>
        get() = _offersData

    fun getOffers(offset: String){
        OfferListRepository.fetchOffers(offset)
    }
}