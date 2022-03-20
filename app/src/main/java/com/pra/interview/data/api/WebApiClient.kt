package com.pra.interview.data.api

import android.content.Context
import com.pra.interview.data.model.TitleResponseModel
import com.pra.myapplication.data.model.api.WebApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WebApiClient(private val context: Context) {
    private val Base_URL = "https://apivegans.veganslab.xyz/";

    private val webApi: WebApi? = null
    var webApiClient: WebApiClient? = null
    private val mContext: Context? = null


    public fun getWebapi(): WebApi {
        val cacheSize = (5 * 1024 * 1024).toLong()

        System.setProperty("http.keepAlive", "false")

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
        client.connectTimeoutMillis

        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(WebApi::class.java)
    }

    fun getTitle(): Call<TitleResponseModel> = getWebapi().getTitle()
}
