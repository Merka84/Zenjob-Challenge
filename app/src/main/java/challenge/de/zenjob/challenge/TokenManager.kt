package challenge.de.zenjob.challenge

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.util.Base64
import java.nio.charset.Charset
import java.security.NoSuchAlgorithmException
import java.security.spec.InvalidKeySpecException
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created on 2018-11-23, 2:25 PM.
 * @author Akram Shokri
 */

class TokenManager {

    companion object {
        val TOKEN_TYPE_KEY = "__type"
        val TOKEN_VAL_KEY = "__val"
        val PREFS_FILENAME = "de.zenjob.prefs"

       fun saveToken(token: String, tokenType: String){
           val prefs: SharedPreferences = MainApplication.applicationContext().getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE)
           prefs.edit().putString(TOKEN_TYPE_KEY, Encryptor.encrypt(tokenType)).apply()
           prefs.edit().putString(TOKEN_VAL_KEY, Encryptor.encrypt(token)).apply()
       }

        fun token() : String?{
            return readKey(TOKEN_VAL_KEY)
        }

        fun tokenType() : String?{
            return readKey(TOKEN_TYPE_KEY)
        }

      private fun readKey(key : String) : String? {
           val prefs: SharedPreferences = MainApplication.applicationContext().getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE)
           val t = prefs.getString(key, null)
          return if (t != null) {
               Encryptor.decrypt(t)
           } else{
               null
           }
       }

    }
}

object Encryptor {

    private val pswdIterations = 10
    private val keySize = 128
    private val cypherInstance = "AES/CBC/PKCS5Padding"
    private val secretKeyInstance = "PBKDF2WithHmacSHA1"
    private val plainText = "textOnly??"
    private val AESSalt = "SmerKakA1L0T"
    private val initializationVector = "8119745113154120"

    @Throws(Exception::class)
    fun encrypt(textToEncrypt: String): String {

        val skeySpec = SecretKeySpec(getRaw(plainText, AESSalt), "AES")
        val cipher = Cipher.getInstance(cypherInstance)
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, IvParameterSpec(initializationVector.toByteArray()))
        val encrypted = cipher.doFinal(textToEncrypt.toByteArray())
        return Base64.encodeToString(encrypted, Base64.DEFAULT)
    }

    @Throws(Exception::class)
    fun decrypt(textToDecrypt: String): String {

        val encryted_bytes = Base64.decode(textToDecrypt, Base64.DEFAULT)
        val skeySpec = SecretKeySpec(getRaw(plainText, AESSalt), "AES")
        val cipher = Cipher.getInstance(cypherInstance)
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, IvParameterSpec(initializationVector.toByteArray()))
        val decrypted = cipher.doFinal(encryted_bytes)
        return String(decrypted, Charset.forName("UTF-8"))
    }

    private fun getRaw(plainText: String, salt: String): ByteArray {
        try {
            val factory = SecretKeyFactory.getInstance(secretKeyInstance)
            val spec = PBEKeySpec(plainText.toCharArray(), salt.toByteArray(), pswdIterations, keySize)
            return factory.generateSecret(spec).encoded
        } catch (e: InvalidKeySpecException) {
            e.printStackTrace()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ByteArray(0)
    }

}