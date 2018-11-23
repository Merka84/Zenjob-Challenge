package challenge.de.zenjob.challenge.epoxy.controller

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.view.View
import challenge.de.zenjob.challenge.MainApplication
import challenge.de.zenjob.challenge.R
import challenge.de.zenjob.challenge.activity.OfferDetailActivity
import challenge.de.zenjob.challenge.epoxy.model.HeaderModel_
import challenge.de.zenjob.challenge.epoxy.model.OfferEpoxyModel_
import challenge.de.zenjob.challenge.repository.model.OffersModel
import com.airbnb.epoxy.EpoxyController

/**
 * Created on 2018-11-16, 3:34 AM.
 * @author Akram Shokri
 */

class OfferController(var activity: AppCompatActivity) : EpoxyController() {

    var offerList: OffersModel? = null

    override fun buildModels() {
        var i: Long = 0

        HeaderModel_()
            .id("header")
            .headerAllJobTitle(totalJobCountSubHeader(offerList?.total))
            .addTo(this)

        offerList?.offers?.forEach { offer ->
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

    private fun totalJobCountSubHeader(totalCount : Int?) : String {
        val total = totalCount ?: 0
        return when {
            total > 1 -> "$total ${MainApplication.applicationContext().getString(R.string.job_sub_header)}"
            total == 1 -> "$total ${MainApplication.applicationContext().getString(R.string.job_sub_header_single)}"
            else -> MainApplication.applicationContext().getString(R.string.job_sub_header_none)
        }
    }

}
