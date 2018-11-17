package challenge.de.zenjob.challenge.epoxy.model

import android.view.View
import android.widget.TextView
import challenge.de.zenjob.challenge.R
import challenge.de.zenjob.challenge.repository.model.Location
import challenge.de.zenjob.challenge.repository.model.ShiftsItem
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.activity_offer_list_item.view.*

/**
 * Created on 2018-11-16 2:33 AM.
 * @author Akram Shokri
 */

@EpoxyModelClass(layout = R.layout.activity_offer_list_item)
abstract class OfferEpoxyModel: EpoxyModelWithHolder<OfferEpoxyModel.OfferHolder>() {

    @EpoxyAttribute
    var earningTotal: String = ""

    @EpoxyAttribute
    var companyName: String = ""

    @EpoxyAttribute
    var description: String = ""

    @EpoxyAttribute
    var title: String = ""

    @EpoxyAttribute
    var hourSum: String? = ""

    @EpoxyAttribute
    var earningHourly: String = ""

    @EpoxyAttribute
    var shifts: List<ShiftsItem>? = ArrayList()

    @EpoxyAttribute
    var location: Location? = Location()

    @EpoxyAttribute
    var offerId: String = ""
    
    var jobCategoryKey: String = ""

    override fun bind(holder: OfferHolder) {
        holder.jobTitleView?.text = earningTotal
        holder.jobSubTitleView?.text = earningHourly
        holder.descriptionView?.text = description
        holder.workDayView?.text = shifts?.get(0)?.beginDate
        holder.workHourView?.text = shifts?.get(0)?.endDate
        holder.cityView?.text = location?.city
        holder.districtView?.text = location?.district
    }

    inner class OfferHolder : EpoxyHolder(){

        lateinit var jobTitleView: TextView
        lateinit var jobSubTitleView:TextView
        lateinit var descriptionView:TextView
        lateinit var workDayView:TextView
        lateinit var workHourView:TextView
        lateinit var cityView:TextView
        lateinit var districtView:TextView

        override fun bindView(itemView: View) {
            jobTitleView = itemView.jobSubTitleView
            jobSubTitleView = itemView.jobSubTitleView
            descriptionView = itemView.descriptionView
            workDayView = itemView.workDayView
            workHourView = itemView.workHourView
            cityView = itemView.cityView
            districtView = itemView.districtView

        }


    }
}