package challenge.de.zenjob.challenge.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import challenge.de.zenjob.challenge.R
import challenge.de.zenjob.challenge.epoxy.controller.OfferController
import challenge.de.zenjob.challenge.repository.model.OffersModel
import challenge.de.zenjob.challenge.viewmodel.OfferListViewModel

/**
 * Created on 2018-11-16, 4:22 AM.
 * @author Akram Shokri
 */
class OfferListActivity : AppCompatActivity() {
    private var offerListVM: OfferListViewModel? = null
    private var offset = 0

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.offerRecycleView) }

    private val offerController: OfferController by lazy { OfferController(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.offer_list)
        setUpObserver()
        initRecycler()
    }

    private fun setUpObserver() {
        offerListVM = ViewModelProviders.of(this).get(OfferListViewModel::class.java)
        offerListVM?.getOffers("$offset")
        offerListVM?.offersData?.observe(this, Observer { dataWrapper ->

            if (dataWrapper?.data != null) {
                if (offset == 0) {
                    offerController.offerList = dataWrapper?.data

                } else if (dataWrapper.data.offers != null) { //adding more loaded offers

                    dataWrapper.data.offers.forEach {
                        val exist = offerController.offerList?.offers?.contains(it) ?: false
                        if (!exist) {
                            offerController.offerList?.offers?.add(it)
                        }
                    }

                    offerController.offerList?.total = dataWrapper.data.total
                    offerController.offerList?.offset = dataWrapper.data.offset
                    offerController.offerList?.max = dataWrapper.data.max
                }
                offerController.requestModelBuild()
                loadMore(dataWrapper?.data)
            } else {
                Toast.makeText(this, dataWrapper?.errorMessage, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun loadMore(data: OffersModel) {
        if (offset < data.total) {
            offset += data.max
        }
        if (offset < data.total) {
            offerListVM?.getOffers("$offset")
        }
    }


    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            layoutManager = linearLayoutManager
            setHasFixedSize(true)
            adapter = offerController.adapter
            addItemDecoration(
                DividerItemDecoration(
                    this@OfferListActivity,
                    linearLayoutManager.orientation
                )
            )
        }

        offerController.requestModelBuild()
    }
}