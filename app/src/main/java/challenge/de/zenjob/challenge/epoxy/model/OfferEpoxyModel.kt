package challenge.de.zenjob.challenge.epoxy.model

import android.support.constraint.Group
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import challenge.de.zenjob.challenge.R
import challenge.de.zenjob.challenge.repository.model.Location
import challenge.de.zenjob.challenge.repository.model.ShiftsItem
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyHolder
import com.airbnb.epoxy.EpoxyModelClass
import com.airbnb.epoxy.EpoxyModelWithHolder
import kotlinx.android.synthetic.main.activity_offer_list_item.view.*
import java.text.SimpleDateFormat

import com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash

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

    @EpoxyAttribute(DoNotHash)
    var arrowClickListener : View.OnClickListener? = null

    override fun bind(holder: OfferHolder) {
        holder.jobTitleView?.text = earningTotal
        holder.jobSubTitleView?.text = earningHourly
        holder.descriptionView?.text = description
        holder.cityView?.text = location?.city
        holder.districtView?.text = location?.district
        holder.bannerView?.setBackgroundResource(whichBackground())
        holder.readMoreArrow.setOnClickListener(arrowClickListener)
        holder.readMoreText.setOnClickListener(arrowClickListener)

        bindWorkShifts(holder)
    }


    private fun bindWorkShifts(holder: OfferHolder) {

        holder.workDayView?.text = DateConverter.getDate(shifts?.get(0)?.beginDate)
        holder.workHourView?.text = "${DateConverter.getTime(shifts?.get(0)?.beginDate)} - ${DateConverter.getTime(shifts?.get(0)?.endDate)}"

        val count = shifts?.size ?: 0

        if(count > 1){
            holder.shiftGroup.visibility = View.VISIBLE
            holder.workDayViewEnd?.text = DateConverter.getDate(shifts?.get(count-1)?.beginDate)
            holder.workHourViewEnd?.text = "${DateConverter.getTime(shifts?.get(count-1)?.beginDate)} - ${DateConverter.getTime(shifts?.get(count-1)?.endDate)}"
            if(count > 2) {
                holder.shiftCount.text = if ((count-2) > 1)  "+ ${count-2} Shifts" else  "+ ${count-2} Shift"
            } else{
                holder.shiftCount.visibility = View.GONE
            }
        } else {
            holder.shiftGroup.visibility = View.GONE
        }

    }

    private fun whichBackground() : Int{
       return if ((super.id() % 2) == 0L) R.drawable.gradient_bg_orange_upper_round
        else R.drawable.gradient_bg_violet_upper_round
    }

    inner class OfferHolder : EpoxyHolder(){

        lateinit var jobTitleView: TextView
        lateinit var jobSubTitleView:TextView
        lateinit var descriptionView:TextView
        lateinit var workDayView:TextView
        lateinit var workHourView:TextView
        lateinit var workDayViewEnd:TextView
        lateinit var workHourViewEnd:TextView
        lateinit var cityView:TextView
        lateinit var districtView:TextView
        lateinit var shiftCount:TextView
        lateinit var bannerView : View
        lateinit var shiftGroup : Group
        lateinit var readMoreArrow : ImageView
        lateinit var readMoreText : TextView


        override fun bindView(itemView: View) {
            jobTitleView = itemView.jobTitleView
            jobSubTitleView = itemView.jobSubTitleView
            descriptionView = itemView.descriptionView
            workDayView = itemView.workDayView
            workHourView = itemView.workHourView
            cityView = itemView.cityView
            districtView = itemView.districtView
            bannerView = itemView.bannerView
            shiftGroup = itemView.shiftGroup
            workDayViewEnd = itemView.workDayView2
            workHourViewEnd = itemView.workHourView2
            shiftCount = itemView.shiftCount
            readMoreArrow = itemView.seeMoreArrow
            readMoreText = itemView.moreJob
        }

    }
}

object DateConverter {

    fun getDate(input: String?) : String {
       val res = convertFormat(input)
       return res.split(",")[0]
    }

    fun getTime(input: String?) : String {
        val res = convertFormat(input)
        return res.split(",")[2]
    }

    private fun convertFormat(input : String?) : String{
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        val requiredFormat = SimpleDateFormat("EEE.d.MM,yyyy,HH:mm")
        return requiredFormat.format(format.parse(input)).toString()
    }
}