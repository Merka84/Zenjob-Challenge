package challenge.de.zenjob.challenge.repository.model

import com.google.gson.annotations.SerializedName

data class LoginModel(@SerializedName("access_token")
                      val accessToken: String = "",

                      @SerializedName("refresh_token")
                      val refreshToken: String = "",

                      @SerializedName("roles")
                      val roles: List<String>?,

                      @SerializedName("token_type")
                      val tokenType: String = "",

                      @SerializedName("expires_in")
                      val expiresIn: Int = 3600,

                      @SerializedName("username")
                      val username: String = "")