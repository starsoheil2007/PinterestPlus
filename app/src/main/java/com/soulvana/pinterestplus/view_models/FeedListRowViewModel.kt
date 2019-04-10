package com.soulvana.pinterestplus.view_models

import com.soulvana.pinterestplus.R
import com.soulvana.pinterestplus.models.FeedModel


class FeedListRowViewModel : BaseViewModel() {

    var oneFeed: FeedModel? = null

    /**
     * Bind each row to view model
     *
     * @param oneFeed instance of FeedModel
     */
    fun bind(oneFeed: FeedModel) {
        this.oneFeed = oneFeed
    }

    /**
     * get Main Post Image Url
     *
     */
    fun getMainImageUrl(): String {
        if (oneFeed != null) {
            return oneFeed!!.urls.full
        }
        return ""
    }

    /**
     * get User profile image url
     *
     */
    fun getProfileImageUrl(): String {
        if (oneFeed != null) {
            return oneFeed!!.user.profileImage.large
        }
        return ""
    }

    /**
     * get User name of post
     *
     */
    fun getUserName(): String {
        if (oneFeed != null) {
            return oneFeed!!.user.username
        }
        return "Loading ... "
    }

    /**
     * get time of post with imagine format
     *
     */
    fun getTime(): String {
        if (oneFeed != null) {
            val dt = oneFeed!!.createdAt.split("T")
            val time = dt[1].split(":")[0] + ":" + dt[1].split(":")[1]
            return dt[0] + " " + time
        }
        return "Loading ... "
    }

    /**
     * Get like count
     *
     */
    fun getLikeCount(): String {
        if (oneFeed != null) {
            return oneFeed!!.likes.toString()
        }
        return "..."
    }

    /**
     * Get icon of like base user liked post or not ?
     *
     */
    fun getLikedUserIcon(): Int {
        if (oneFeed != null && oneFeed!!.likedByUser)
            return R.mipmap.heart01
        return R.mipmap.heart02
    }
}