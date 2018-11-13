package challenge.de.zenjob.challenge.network.model

import com.google.gson.annotations.SerializedName

/**
 * Created on 2018-11-07, 10:40 PM.
 * @author Akram Shokri
 */

data class LoginData(val email: String, val password: Int)

data class LoginResponse (val email : String){

    @SerializedName("username") var userName = email
    @SerializedName("token_type") var tokenType = ""
    @SerializedName("access_token") var accessToken = ""
    @SerializedName("expires_in") var expireIn = 3600
    @SerializedName("refresh_token") var refreshToken = ""

}