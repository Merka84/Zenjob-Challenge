package challenge.de.zenjob.challenge.repository.model

import com.google.gson.annotations.SerializedName

data class OffersModel(@SerializedName("total")
                       var total: Int = 0,
                       @SerializedName("offset")
                       var offset: Int = 0,
                       @SerializedName("max")
                       var max: Int = 0,
                       @SerializedName("newestTimestamp")
                       val newestTimestamp: Long = 0,
                       @SerializedName("offers")
                       val offers: ArrayList<OfferModel>?)