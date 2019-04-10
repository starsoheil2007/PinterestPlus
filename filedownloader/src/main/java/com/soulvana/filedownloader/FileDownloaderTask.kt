package com.soulvana.filedownloader

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import java.io.BufferedInputStream
import java.io.ByteArrayOutputStream
import java.net.HttpURLConnection
import java.net.URL


class FileDownloaderTask : AsyncTask<String, String, String?> {

    private lateinit var memoryCache: MemoryCache
    @SuppressLint("StaticFieldLeak")
    private lateinit var url: String
    private lateinit var downloadingFileModel: DownloadingFileModel

    /**
     * @param memoryCache instance of memory Cache
     */
    constructor(url: String, memoryCache: MemoryCache, downloadingFileModel: DownloadingFileModel) {
        this.url = url
        this.memoryCache = memoryCache
        this.downloadingFileModel = downloadingFileModel
        this.downloadingFileModel.setTaskHandler(this)
        this.memoryCache.putFileInMemory(url, downloadingFileModel)
    }


    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        /*
        Maybe another task Evict this file from memory on background
        I check this and if task is not in memory i reinsert this
        This happen most number of file in cache config is low
         */
        if (memoryCache.getFileFromMemory(url) == null) {
            memoryCache.putFileInMemory(url, this.downloadingFileModel)
        }
        memoryCache.getFileFromMemory(url)!!.setIsDownloading(false)
    }

    override fun doInBackground(vararg urls: String?): String? {
        return downloadFile(this.url)
    }


    override fun onPreExecute() {
        super.onPreExecute()
        /*
        Maybe another task Evict this file from memory on background
        I check this and if task is not in memory i reinsert this
        This happen most number of file in cache config is low
         */
        if (memoryCache.getFileFromMemory(url) == null) {
            memoryCache.putFileInMemory(url, this.downloadingFileModel)
        }
        //Call on Start download
        callAllOnStartDownloadListener()
        //Set is Downloading True for another task dont start it and wait for this
        memoryCache.getFileFromMemory(url)!!.setIsDownloading(true)
    }


    /**
     * Download File Task
     *
     * @param url url of file
     */
    private fun downloadFile(url: String?): String? {
        var count: Int
        var urlConnection: HttpURLConnection? = null
        try {

            val uri = URL(url)
            urlConnection = uri.openConnection() as HttpURLConnection

            val statusCode = urlConnection!!.getResponseCode()
            if (statusCode != HttpURLConnection.HTTP_OK) {
                callAllOnConnectionErrorListener()
                return null
            }

            callAllOnDownloadingListener()

            // getting file length
            val lenghtOfFile = urlConnection.contentLength

            val input = BufferedInputStream(uri.openStream(), 8192)


            val output = ByteArrayOutputStream(lenghtOfFile)
            val data = ByteArray(1024)


            var total: Long = 0
            count = input.read(data)
            while (count != -1) {
                total += count.toLong()

                // writing data to file
                output.write(data, 0, count)
                count = input.read(data)
            }

            // flushing output
            output.flush()

            // closing streams
            output.close()
            input.close()
            callAllOnFinishDownloadListener(output)

        } catch (e: Exception) {
            Log.d("URLCONNECTIONERROR", e.toString())
            callAllOnConnectionErrorListener()
            if (urlConnection != null) {
                urlConnection!!.disconnect()
            }
            Log.w("ImageDownloader", "Error downloading image from $url")
        } finally {
            if (urlConnection != null) {
                urlConnection!!.disconnect()

            }
            callAllOnDownloadNothingListener()
        }
        return null
    }


    /**
     * Call all requested this url listener for OnStart
     *
     */
    private fun callAllOnStartDownloadListener() {
        var fileFromMemory = memoryCache.getFileFromMemory(url)
        fileFromMemory?.getListenerList()?.forEach { listener ->
            listener.onStartDownload()
        }
    }

    /**
     * Call all requested this url listener for OnDownload
     *
     */
    private fun callAllOnDownloadingListener() {
        var fileFromMemory = memoryCache.getFileFromMemory(url)
        fileFromMemory?.getListenerList()?.forEach { listener ->
            listener.onDownloading()
        }
    }

    /**
     * Call all requested this url listener for OnFinish
     * @param outputStream downloaded stream
     *
     */
    private fun callAllOnFinishDownloadListener(outputStream: ByteArrayOutputStream) {
        var fileFromMemory = memoryCache.getFileFromMemory(url)
        fileFromMemory?.getListenerList()?.forEach { listener ->
            listener.onFinishDownload(outputStream)
        }
    }

    /**
     * Call all requested this url listener for OnDownloadNothing
     *
     */
    private fun callAllOnDownloadNothingListener() {
        var fileFromMemory = memoryCache.getFileFromMemory(url)
        fileFromMemory?.getListenerList()?.forEach { listener ->
            listener.onDownloadNothing()
        }
    }

    /**
     * Call all requested this url listener for ConnectionError
     *
     */
    private fun callAllOnConnectionErrorListener() {
        var fileFromMemory = memoryCache.getFileFromMemory(url)
        fileFromMemory?.getListenerList()?.forEach { listener ->
            listener.onConnectionError()
        }
    }

}