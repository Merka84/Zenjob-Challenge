package challenge.de.zenjob.challenge.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import challenge.de.zenjob.challenge.repository.OfferListRepository
import challenge.de.zenjob.challenge.repository.model.DataWraper
import challenge.de.zenjob.challenge.repository.model.OffersModel

/**
 * Created on 2018-11-23, 3:52 PM.
 * @author Akram Shokri
 */
class OfferListViewModel : ViewModel() {
    private val _offersData = OfferListRepository.offerListResponse

    val offersData : LiveData<DataWraper<OffersModel>>
        get() = _offersData

    fun getOffers(offset: String){
        OfferListRepository.fetchOffers(offset)
    }
}



//class ActivityViewModel(app: Application) : AndroidViewModel(app) {
//    val db by lazy {
//        Room.inMemoryDatabaseBuilder(app, PagingDatabase::class.java).build()
//    }
//    val pagedList: LiveData<PagedList<DataWraper<OffersModel>>> by lazy {
//        LivePagedListBuilder<Int, DataWraper<OffersModel>>(
//            db.userDao().dataSource, 100
//        ).build()
//    }
//
//    init {
//        bg {
//            (1..3000).map {
//                User(it)
//            }.let {
//                it.groupBy {
//                    it.uid / 200
//                }.forEach { group ->
//                    launch(CommonPool) {
//                        delay(group.key.toLong(), TimeUnit.SECONDS)
//                        db.userDao().insertAll(group.value)
//                    }
//                }
//            }
//        }
//    }
//}