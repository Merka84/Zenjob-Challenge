package challenge.de.zenjob.challenge.epoxy.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import challenge.de.zenjob.challenge.R
import challenge.de.zenjob.challenge.activity.OfferDetailActivity
import challenge.de.zenjob.challenge.epoxy.model.HeaderModel_
import challenge.de.zenjob.challenge.epoxy.model.OfferEpoxyModel
import challenge.de.zenjob.challenge.epoxy.model.OfferEpoxyModel_
import challenge.de.zenjob.challenge.repository.DataRepository
import com.airbnb.epoxy.EpoxyController

/**
 * Created on 2018-11-16, 3:34 AM.
 * @author Akram Shokri
 */

class OfferController(var activity: AppCompatActivity) : EpoxyController() {

    var offerList: List<OfferEpoxyModel>? = null

    init {
        DataRepository.activity = activity
    }

    override fun buildModels() {
        var i: Long = 0

        HeaderModel_()
            .id("header")
            .headerAllJobTitle(DataRepository.totalJobCountSubHeader())
            .addTo(this)

        DataRepository.getOffers().offers?.forEach { offer ->
            OfferEpoxyModel_()
                .id(i++)
                .offerId(offer.offerId)
                .title(offer.title)
                .description(offer.description)
                .earningTotal(offer.earningTotal)
                .earningHourly("${offer.earningHourly}  ${activity.getString(R.string.per_hour)}")
                .hourSum(offer?.hourSum)
                .companyName(offer.companyName)
                .shifts(offer.shifts)
                .location(offer.location)
                .arrowClickListener(createClickListener(offer.offerId))
                .addTo(this)
        }
    }

    private fun createClickListener(offerId : String) : View.OnClickListener{
        val intent = Intent (activity, OfferDetailActivity::class.java)
        intent.putExtra(OfferDetailActivity.OFFER_ID_KEY , offerId)
        return View.OnClickListener { activity.startActivity(intent) }
    }

}
