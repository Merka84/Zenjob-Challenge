package challenge.de.zenjob.challenge.epoxy.model

import android.view.View
import android.widget.TextView
import challenge.de.zenjob.challenge.R
import com.airbnb.epoxy.*
import kotlinx.android.synthetic.main.activity_offer_list_header.view.*


/**
 * Created on 2018-11-16, 8:11 PM.
 * @author Akram Shokri
 */

@EpoxyModelClass(layout = R.layout.activity_offer_list_header)
abstract class HeaderModel : EpoxyModelWithHolder<HeaderModel.Header>() {

    @EpoxyAttribute
    var headerAllJobTitle: String = ""

    override fun bind(holder: HeaderModel.Header) {
        holder.jobsSubTitle.text = headerAllJobTitle
    }

    inner class Header : EpoxyHolder() {
        lateinit var jobsSubTitle:TextView

        override fun bindView(itemView: View) {
            jobsSubTitle = itemView.headerSubtitle
        }

    }
}