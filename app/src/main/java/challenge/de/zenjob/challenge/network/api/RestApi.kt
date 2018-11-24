package challenge.de.zenjob.challenge.network.api

import challenge.de.zenjob.challenge.repository.model.LoginModel
import challenge.de.zenjob.challenge.repository.model.LoginParam
import challenge.de.zenjob.challenge.repository.model.OfferModel
import challenge.de.zenjob.challenge.repository.model.OffersModel
import retrofit2.Call
import retrofit2.http.*

/**
 * Created on 2018-11-08, 12:38 AM.
 * @author Akram Shokri
 */

/**
 * I have put all the service APIs here instead of putting each set of related APIs in a
 * separate interface because it was only 3 of them in this test project
 * Headers are set in {@link RestApi challenge.de.zenjob.challenge.network.RestApi}
 */

interface RestApi{

   @POST("/api/employee/v1/auth")
   fun login(@Body loginParam: LoginParam) : Call<LoginModel>

   @GET("/api/employee/v1/offers")
   fun fetchOffers(@Query("offset") offset : String) : Call<OffersModel>

   @GET("/api/employee/v1/offers/{id}")
   fun fetchOfferById(@Path("id") id : String) : Call<OfferModel>
}