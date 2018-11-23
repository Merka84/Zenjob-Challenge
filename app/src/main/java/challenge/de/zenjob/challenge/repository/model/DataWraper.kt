package challenge.de.zenjob.challenge.repository.model

/**
 * Created on 2018-11-23, 11:33 AM.
 * @author Akram Shokri
 */
class DataWraper<T> (data: T?, msg: String) {
    val data : T? = data
    val errorMessage = msg

}