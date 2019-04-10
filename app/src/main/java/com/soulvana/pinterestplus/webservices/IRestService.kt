package com.drquizup.app.webservices


import com.soulvana.pinterestplus.models.FeedModel
import io.reactivex.Observable
import retrofit2.http.GET

interface IRestService {


    /**
     * Get list of pinterest post
     *
     */
    @GET("raw/wgkJgazE")
    fun getFeedList(): Observable<ArrayList<FeedModel>>


}