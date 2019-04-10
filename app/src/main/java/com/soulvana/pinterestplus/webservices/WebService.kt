package com.drquizup.app.webservices

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.soulvana.pinterestplus.BuildConfig
import com.soulvana.pinterestplus.models.FeedModel
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Web Service Class to init Retrofit and call rest service
 *
 */
class WebService {

    companion object {
        private const val WEB_SERVICE_URL = "http://pastebin.com/"

        val instance = WebService()

    }

    private var service: IRestService

    /**
     * init retrofit with logger in Gson Converter
     *
     */
    private fun provideRetrofitInterface(): Retrofit {

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        // set Timeouts
        httpClient.connectTimeout(20, TimeUnit.SECONDS)
        httpClient.readTimeout(20, TimeUnit.SECONDS)
        httpClient.writeTimeout(30, TimeUnit.SECONDS)
        // Show log in Debug
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(logging)
        }

        // Config Gson
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

        // Init Retrofit
        return Retrofit.Builder()
            .baseUrl(WEB_SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(httpClient.build())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    /**
     * Init class in constructor called
     */
    init {
        val retrofit = provideRetrofitInterface()
        //Create retrofit service from IRestService
        service = retrofit.create(IRestService::class.java)
    }


    /**
     * Get list of pinterest post
     *
     */
    fun getFeedList(): Observable<ArrayList<FeedModel>> {
        return service.getFeedList()
    }


}