package com.app.lorempiscum.network

import com.app.lorempiscum.model.remote.ResponseImages
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteLoremApi {
    @GET("v2/list")
    fun requestImages(
        @Query("page") page: Int
    )
            : Observable<List<ResponseImages>>
}