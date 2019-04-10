package com.soulvana.pinterestplus.views.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.soulvana.pinterestplus.R
import com.soulvana.pinterestplus.view_models.MainActivityViewModel
import com.soulvana.pinterestplus.view_models.ToolBarViewModel
import com.soulvana.pinterestplus.views.factories.MainViewModelFactory
import io.reactivex.disposables.Disposable

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = "MainActivity"
    }


    private lateinit var binding: com.soulvana.pinterestplus.databinding.ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var layoutManager: LinearLayoutManager

    //Handle Rest service
    private lateinit var callRest: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding view
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, MainViewModelFactory(this)).get(MainActivityViewModel::class.java)

        // Init RecyclerView LayoutManager
        layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.mainList.layoutManager = layoutManager
        //init toolbar
        initToolBar()

        //init View mpdel for show
        bindingViewModel()

        binding.pullToRefresh.setOnRefreshListener {
            //init View mpdel for show
            bindingViewModel()
        }


    }

    private fun bindingViewModel() {
        viewModel.bind(this, binding)
        binding.viewModel = viewModel
        callRest = viewModel.callRest
    }

    /**
     * This Function init Toolbar and show toolbar title
     *
     */
    private fun initToolBar() {
        setSupportActionBar(binding.toolBar.mainToolbar)
        if (supportActionBar != null)
            supportActionBar!!.setDisplayShowTitleEnabled(false)
        val toolbarViewModel = ToolBarViewModel()
        toolbarViewModel.bind(getString(R.string.main_title))
        binding.toolBar.toolBarViewModel = toolbarViewModel
    }

    /**
     * Cancel UnCompleted rest call
     *
     */
    override fun onStop() {
        if (::callRest.isInitialized && !callRest.isDisposed)
            callRest.dispose()
        super.onStop()
    }

}
