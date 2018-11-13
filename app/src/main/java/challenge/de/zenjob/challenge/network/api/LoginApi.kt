package challenge.de.zenjob.challenge.network.api

import retrofit2.http.POST

/**
 * Created on 2018-11-08, 12:38 AM.
 * @author Akram Shokri
 */

interface LoginApi{

   @POST("/api/employee/v1/auth")
   fun authenticate()
}