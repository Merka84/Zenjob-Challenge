package challenge.de.zenjob.challenge.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import challenge.de.zenjob.challenge.R
import challenge.de.zenjob.challenge.databinding.ActivityOfferDetailBinding
import challenge.de.zenjob.challenge.repository.DataRepository

/**
 * Created on 2018-11-10, 12:43 AM.
 * @author Akram Shokri
 */

class OfferDetailActivity : AppCompatActivity() {


    private val backClickListener = View.OnClickListener(function = {
        super.onBackPressed()
    })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityOfferDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_offer_detail)
        val offerId = intent.getStringExtra(OFFER_ID_KEY)
        val offer = DataRepository.getOffer(offerId)
        binding.offer = offer
        binding.listener = backClickListener
        binding.executePendingBindings()
    }

    companion object {
        val OFFER_ID_KEY = "offerId"
    }
}