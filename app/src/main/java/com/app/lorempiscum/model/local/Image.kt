package com.app.lorempiscum.model.local

import com.app.lorempiscum.base.BaseModel
import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("url") var url: String? = null
) : BaseModel()