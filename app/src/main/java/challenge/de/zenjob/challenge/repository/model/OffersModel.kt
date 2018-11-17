package challenge.de.zenjob.challenge.repository.model

import com.google.gson.annotations.SerializedName

data class OffersModel(@SerializedName("total")
                       val total: Int = 0,
                       @SerializedName("offset")
                       val offset: Int = 0,
                       @SerializedName("max")
                       val max: Int = 0,
                       @SerializedName("newestTimestamp")
                       val newestTimestamp: Long = 0,
                       @SerializedName("offers")
                       val offers: List<OfferModel>?)