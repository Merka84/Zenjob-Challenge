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
                      val username: String = "",

                      @SerializedName("error")
                      val error: Error?)

data class Error(@SerializedName("code")
                 val code: String = "",
                 @SerializedName("message")
                 val message: String = "")



data class LoginParam(@SerializedName("username")
                      val user: String = "",

                      @SerializedName("password")
                      val password: String = "")