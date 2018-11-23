package challenge.de.zenjob.challenge.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import challenge.de.zenjob.challenge.R
import challenge.de.zenjob.challenge.epoxy.controller.OfferController

/**
 * Created on 2018-11-16, 4:22 AM.
 * @author Akram Shokri
 */
class OfferListActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by lazy { findViewById<RecyclerView>(R.id.offerRecycleView) }

    private val offerController: OfferController by lazy { OfferController(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.offer_list)
        initRecycler()
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