package com.soulvana.filedownloader

import android.annotation.SuppressLint
import android.content.Context

class FileDownloader {


    companion object {
        @SuppressLint("StaticFieldLeak")
        private var numberOfCacheImage: Int = 5
        private lateinit var memoryCache: MemoryCache
        /**
         * Init FileDownloader library with configuration
         *
         * @param numberOfCacheImage Number of images that cashing into memory
         *
         * @return Intance or FileDownloader for use in all application
         */
        fun initLibrary(numberOfCacheImage: Int): FileDownloader {
            this.numberOfCacheImage = numberOfCacheImage
            memoryCache = MemoryCache(numberOfCacheImage)
            return FileDownloader()
        }
    }

    /**
     * Download File Task
     *
     *
     * @param url url of file
     * @param downloadListener File listener
     *
     * @return instance of DownloadingFileModel for handle request (You can call cancelDownload for downloading file)
     */

    fun DownloadFile(url: String, downloadListener: DownloadFileListener): DownloadingFileModel {
        // first check file requested before
        var checkFileBeforeRequested = checkFileBeforeRequested(url)
        // if requested check file is in downloading state or not
        if (checkFileBeforeRequested != null) {
            if (!checkFileBeforeRequested.isDownloading()) { // Download is Complete
                downloadListener.onFinishDownload(checkFileBeforeRequested.getOutputStream())
                checkFileBeforeRequested.refreshTimeUsed()
            } else // if file is in downloading state we put new Listener for handle result for new request
                checkFileBeforeRequested.addNewListener(downloadListener)
            return checkFileBeforeRequested
        } else {
            //start new download task
            var newDownaload = DownloadingFileModel(downloadListener)
            FileDownloaderTask(url, memoryCache, newDownaload).execute(null)
            return newDownaload
        }
    }

    /**
     * Check before requested download
     *
     * @param url url of file
     */
    private fun checkFileBeforeRequested(url: String): DownloadingFileModel? {
        return memoryCache.getFileFromMemory(url)
    }

    /**
     * Get size item in memory for test
     *
     */
    public fun getMemoryCacheSize(): Int {
        return memoryCache.fileMemoryCache.size
    }

    /**
     * check is url in memory for test
     *
     */
    public fun checkIsInMemory(url: String): Boolean {
        return memoryCache.getFileFromMemory(url) != null
    }


}