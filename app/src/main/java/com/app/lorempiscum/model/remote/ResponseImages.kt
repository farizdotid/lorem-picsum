package com.app.lorempiscum.model.remote

import com.app.lorempiscum.base.BaseModel
import com.google.gson.annotations.SerializedName

data class ResponseImages(
    @SerializedName("id") var id: String? = null,
    @SerializedName("author") var author: String? = null,
    @SerializedName("url") var url: String? = null,
    @SerializedName("download_url") var downloadUrl: String? = null
) : BaseModel()