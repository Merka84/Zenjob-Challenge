package challenge.de.zenjob.challenge

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

/**
 * Created on 2018-11-24, 3:13 PM.
 * @author Akram Shokri
 */


        inline fun <reified T> mock() = Mockito.mock(T::class.java)
        inline fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)
