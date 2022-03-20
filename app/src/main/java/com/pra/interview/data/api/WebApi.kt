package com.pra.myapplication.data.model.api

import com.pra.interview.data.model.TitleResponseModel
import retrofit2.Call
import retrofit2.http.GET

interface WebApi {

    //https://apivegans.veganslab.xyz/test.json
    @GET("test.json")
    fun getTitle(): Call<TitleResponseModel>

}