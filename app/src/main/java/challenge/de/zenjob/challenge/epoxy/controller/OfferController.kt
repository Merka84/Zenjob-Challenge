package challenge.de.zenjob.challenge.epoxy.controller

import android.support.v7.app.AppCompatActivity
import challenge.de.zenjob.challenge.epoxy.model.HeaderModel
import challenge.de.zenjob.challenge.epoxy.model.HeaderModel_
import challenge.de.zenjob.challenge.epoxy.model.OfferEpoxyModel
import challenge.de.zenjob.challenge.epoxy.model.OfferEpoxyModel_
import challenge.de.zenjob.challenge.repository.DataRepository
import com.airbnb.epoxy.EpoxyController

/**
 * Created on 2018-11-16, 3:34 AM.
 * @author Akram Shokri
 */

class OfferController (var activity: AppCompatActivity): EpoxyController(){

    var offerList : List<OfferEpoxyModel>? = null

    init {
        DataRepository.activity = activity
    }

    override fun buildModels() {
        var i:Long =0

        HeaderModel_()
            .id("header")
            .headerAllJobTitle(DataRepository.totalJobCountSubHeader())
            .addTo(this)

        DataRepository.getOffers().offers?.forEach {offer ->
            OfferEpoxyModel_()
                .id(i++)
                .offerId(offer.offerId)
                .title(offer.title)
                .description(offer.description)
                .earningTotal(offer.earningTotal)
                .earningHourly(offer.earningHourly)
                .hourSum(offer?.hourSum)
                .companyName(offer.companyName)
                .shifts(offer.shifts)
                .location(offer.location)
                .addTo(this)
        }
    }

}