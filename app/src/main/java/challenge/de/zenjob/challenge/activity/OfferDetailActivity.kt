package challenge.de.zenjob.challenge.activity

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import challenge.de.zenjob.challenge.databinding.ActivityOfferDetailBinding
import challenge.de.zenjob.challenge.viewmodel.OfferViewModel

/**
 * Created on 2018-11-10, 12:43 AM.
 * @author Akram Shokri
 */

class OfferDetailActivity : AppCompatActivity() {
    private var offerVM: OfferViewModel? = null

    private val backClickListener = View.OnClickListener(function = {
        super.onBackPressed()
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        offerVM = ViewModelProviders.of(this).get(OfferViewModel::class.java)
        val binding = ActivityOfferDetailBinding.inflate(layoutInflater)
        binding.viewModel = offerVM
        binding.listener = backClickListener
        binding.setLifecycleOwner(this)

        //get the offer that user has selected from API
        val offerId = intent.getStringExtra(OFFER_ID_KEY)
        offerVM?.getOfferById(offerId)

        binding.executePendingBindings()
        setContentView(binding.root)

    }

    companion object {
        val OFFER_ID_KEY = "offerId"
    }
}