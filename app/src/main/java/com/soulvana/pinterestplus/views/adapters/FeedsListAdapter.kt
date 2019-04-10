package com.soulvana.pinterestplus.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.soulvana.pinterestplus.R
import com.soulvana.pinterestplus.models.FeedModel
import com.soulvana.pinterestplus.view_models.FeedListRowViewModel

/**
 * News list adapter
 *
 * @property context Android app context
 * @property items list of news to be showed
 */
class FeedsListAdapter(var context: Context, var items: ArrayList<FeedModel>) :
    RecyclerView.Adapter<FeedsListAdapter.ViewHolder>() {

    lateinit var binding: com.soulvana.pinterestplus.databinding.ListRowFeedsBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.list_row_feeds, parent, false)
        return ViewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun updateFeedList(items: ArrayList<FeedModel>) {
        this.items = items
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: com.soulvana.pinterestplus.databinding.ListRowFeedsBinding, var context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = FeedListRowViewModel()
        fun bind(oneFeed: FeedModel) {
            viewModel.bind(oneFeed)
            binding.viewModel = viewModel
        }
    }

}