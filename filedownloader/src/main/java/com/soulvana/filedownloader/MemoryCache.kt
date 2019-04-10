package com.soulvana.filedownloader


/**
 * Memory Cache for don't reDownload files is exist in memory
 *
 * @property numberOfFileInMemory maximum number of files in memory for each file or image
 */
class MemoryCache(private var numberOfFileInMemory: Int) {

    public val fileMemoryCache: HashMap<String, DownloadingFileModel> = HashMap()


    /**
     * put downloaded file to memory if completed
     *
     * @param url url of image
     * @param downFileModel new file for download
     */
    fun putFileInMemory(url: String, downFileModel: DownloadingFileModel) {
        if (fileMemoryCache.size == numberOfFileInMemory) { // Remove first index if memory size is maximum (memory as stack)
            evictNotRecentlyUsed()
        }
        fileMemoryCache[url] = downFileModel
    }

    /**
     * Evict not recently used item
     *
     */
    private fun evictNotRecentlyUsed() {
        //Imagine first item is lower
        val firstItem = fileMemoryCache.entries.first()
        var minDate = firstItem.value.getLastDateUsed()
        var minKey = firstItem.key

        //Compare all item to find minimum date time user for use
        fileMemoryCache.forEach { (url, downloadingFileModel) ->
            if (downloadingFileModel.getLastDateUsed() < minDate && !downloadingFileModel.isDownloading()) {
                minDate = downloadingFileModel.getLastDateUsed()
                minKey = url
            }
        }

        //Remove minimum
        fileMemoryCache.remove(minKey)
    }

    /**
     * get downloaded file to memory if exist
     *
     * @param url url of image
     * @return downloaded file path
     */
    fun getFileFromMemory(url: String): DownloadingFileModel? {
        return fileMemoryCache[url]
    }

}
