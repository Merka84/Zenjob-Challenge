package challenge.de.zenjob.challenge.repository.model

import com.google.gson.annotations.SerializedName


data class OfferModel(@SerializedName("instructions")
                      val instructions: String = "",

                      @SerializedName("breakTypes")
                      val breakTypes: List<BreakTypesItem>?,

                      @SerializedName("earningTotal")
                      val earningTotal: String = "",

                      @SerializedName("minutesSum")
                      val minutesSum: String = "",

                      @SerializedName("companyName")
                      val companyName: String = "",

                      @SerializedName("description")
                      val description: String = "",

                      @SerializedName("title")
                      val title: String = "",

                      @SerializedName("hourSum")
                      val hourSum: String? = "",

                      @SerializedName("companyLogoUrl")
                      val companyLogoUrl: String = "",

                      @SerializedName("earningHourly")
                      val earningHourly: String = "",

                      @SerializedName("pricingTables")
                      val pricingTables: List<PricingTablesItem>?,

                      @SerializedName("shifts")
                      val shifts: List<ShiftsItem>?,

                      @SerializedName("location")
                      val location: Location,

                      @SerializedName("id")
                      val offerId: String = "",

                      @SerializedName("jobCategoryKey")
                      val jobCategoryKey: String = "")


data class PricingTablesItem(@SerializedName("times")
                             val times: Object? = null,

                             @SerializedName("earningTotal")
                             val earningTotal: String = "",

                             @SerializedName("minutes")
                             val minutes: Int = 0,

                             @SerializedName("earningHourly")
                             val earningHourly: String = "",

                             @SerializedName("name")
                             val name: String = "",

                             @SerializedName("isSummary")
                             val isSummary: Boolean = false,

                             @SerializedName("unpaid")
                             val unpaid: Boolean = false)


data class Location(@SerializedName("locationName")
                    val locationName: String = "",

                    @SerializedName("streetNumber")
                    val streetNumber: String = "",

                    @SerializedName("city")
                    val city: String = "",

                    @SerializedName("street")
                    val street: String = "",

                    @SerializedName("postalCode")
                    val postalCode: String = "",

                    @SerializedName("district")
                    val district: String = "",

                    @SerializedName("locationLongitude")
                    val locationLongitude: Double = 0.0,

                    @SerializedName("locationLatitude")
                    val locationLatitude: Double = 0.0,

                    @SerializedName("supplementary")
                    val supplementary: Object? = null,

                    @SerializedName("locationSearchString")
                    val locationSearchString: String = ""){
    fun address() : String {
        return "$locationName, $street - $streetNumber, $postalCode - $city"
    }
}


data class BreakTypesItem(@SerializedName("minutes")
                          val minutes: Int = 0,

                          @SerializedName("description")
                          val description: String = "")


data class ShiftsItem(@SerializedName("beginDate")
                      val beginDate: String = "",

                      @SerializedName("breakTypes")
                      val breakTypes: Int = 0,

                      @SerializedName("endDate")
                      val endDate: String = "")