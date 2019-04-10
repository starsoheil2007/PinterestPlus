package com.soulvana.filedownloader

import android.os.AsyncTask
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList

class DownloadingFileModel {

    private var outputStream: ByteArrayOutputStream
    private var isDownloading: Boolean = true
    private var lastDateUsed: Date = Date()
    private var listenerList: ArrayList<DownloadFileListener> = arrayListOf()
    private var taskHandler: AsyncTask<String, String, String?>? = null

    /**
     * For init first time url go for download
     */
    constructor(listener: DownloadFileListener) {
        this.outputStream = ByteArrayOutputStream()
        isDownloading = true
        lastDateUsed = Date()
        listenerList.add(listener)
    }

    public fun addNewListener(listener: DownloadFileListener) {
        listenerList.add(listener)
    }

    fun getLastDateUsed(): Date {
        return this.lastDateUsed
    }

    fun isDownloading(): Boolean {
        return isDownloading
    }

    fun getOutputStream(): ByteArrayOutputStream {
        return this.outputStream
    }

    fun setIsDownloading(isDownloading: Boolean) {
        this.isDownloading = isDownloading
    }

    fun refreshTimeUsed() {
        this.lastDateUsed = Date()
    }

    fun getListenerList(): ArrayList<DownloadFileListener> {
        return this.listenerList
    }


    fun setTaskHandler(taskHandler: AsyncTask<String, String, String?>) {
        this.taskHandler = taskHandler
    }

    fun cancelDownload() {
        if (taskHandler != null && !taskHandler!!.isCancelled) {
            taskHandler!!.cancel(true)
        }
    }

}