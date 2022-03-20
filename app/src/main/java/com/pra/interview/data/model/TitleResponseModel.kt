package com.pra.interview.data.model

import com.google.gson.annotations.SerializedName

data class TitleResponseModel(
    @SerializedName("content")
    var content: List<Content>,
    @SerializedName("lang")
    var lang: String,
    @SerializedName("status")
    var status: Boolean
)