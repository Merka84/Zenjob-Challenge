package challenge.de.zenjob.challenge

import android.app.Application
import android.content.Context

/**
 * Created on 2018-11-23, 2:21 PM.
 * @author Akram Shokri
 */

class MainApplication : Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: MainApplication? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

}