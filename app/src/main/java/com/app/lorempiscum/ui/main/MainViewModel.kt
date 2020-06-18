package com.app.lorempiscum.ui.main

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import com.app.lorempiscum.base.BaseViewModel
import com.app.lorempiscum.model.local.Image
import com.app.lorempiscum.network.RemoteLoremApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val loremApi: RemoteLoremApi) : BaseViewModel() {

    val imageLiveData = MutableLiveData<List<Image>>()

    override fun getLifecycle(): Lifecycle {
        TODO("Not yet implemented")
    }

    fun getImages(page: Int) {
        compositeDisposable.add(
            loremApi.requestImages(page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe {
                    loadingStatus.value = true
                }
                .doOnTerminate {
                    loadingStatus.value = false
                }
                .subscribe({ response ->
                    val images = arrayListOf<Image>()
                    for (image in response) {
                        val downloadUrl = image.downloadUrl
                        images.add(Image(downloadUrl))
                    }
                    imageLiveData.value = images
                    Log.i("debug", "done! size ${images.size}")
                },
                    {

                    })
        )
    }


}