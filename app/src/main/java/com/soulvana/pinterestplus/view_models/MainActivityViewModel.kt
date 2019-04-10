package com.soulvana.pinterestplus.view_models

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.drquizup.app.webservices.WebService
import com.soulvana.pinterestplus.databinding.ActivityMainBinding
import com.soulvana.pinterestplus.models.FeedModel
import com.soulvana.pinterestplus.views.adapters.FeedsListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel : BaseViewModel() {


    public lateinit var feedsListAdapter: FeedsListAdapter
    public lateinit var context: Context
    public lateinit var binding: ActivityMainBinding
    public var isLoading: MutableLiveData<Boolean> = MutableLiveData(false)


    //Handle Rest service
    public lateinit var callRest: Disposable


    fun bind(context: Context, binding: ActivityMainBinding) {
        this.context = context
        this.binding = binding
        getFeedList()
    }

    fun onRefresh() {
        getFeedList()
    }

    fun getAdapter(): FeedsListAdapter {
        if (!::feedsListAdapter.isInitialized || feedsListAdapter == null)
            feedsListAdapter = FeedsListAdapter(context, ArrayList<FeedModel>())
        return feedsListAdapter
    }

    /**
     * Load Pinterest Feed List From Server
     *
     */
    private fun getFeedList() {
        callRest = WebService.instance.getFeedList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                binding.pullToRefresh.isRefreshing = true
            }
            .doOnTerminate {
            }
            .subscribe(

                { result ->
                    binding.pullToRefresh.isRefreshing = false
                    feedsListAdapter.updateFeedList(result)
                },
                { error ->
                    binding.pullToRefresh.isRefreshing = false
                    Toast.makeText(context, "An Error for get Feed List !", Toast.LENGTH_LONG).show()
                }
            )
    }


}