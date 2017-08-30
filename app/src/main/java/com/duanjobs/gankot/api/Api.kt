package com.duanjobs.gankot.api

import com.duanjobs.gankot.bean.ListResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import io.reactivex.Observable

/**
 * Created by duanjobs on 17/8/29.
 */
interface Api {

    companion object Factory {
        fun create(): Api {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()

            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("http://gank.io/")
                    .build()
            return retrofit.create(Api::class.java)
        }
    }

    @GET("api/data/{type}/10/{pageNumber}")
    fun getContent(@Path("type") type: String,
                   @Path("pageNumber") pageNumber: Int): Observable<ListResult>

}