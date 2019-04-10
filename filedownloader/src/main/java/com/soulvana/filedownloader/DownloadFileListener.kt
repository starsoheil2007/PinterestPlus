package com.soulvana.filedownloader

import java.io.ByteArrayOutputStream

interface DownloadFileListener {
    /**
     * On Start Download Executed
     * For show progress bar
     */
    fun onStartDownload()

    /**
     * On Connection is Ok and Download in progress
     *
     */
    fun onDownloading()

    /**
     * On Finishing Download
     *
     * @param outputStream downloaded file inputStream in memory
     */
    fun onFinishDownload(outputStream: ByteArrayOutputStream)

    /**
     * On Download nothing (Url is wrong)
     * for show Replace Image
     */
    fun onDownloadNothing()

    /**
     * On Connect to Url not successful
     *
     */
    fun onConnectionError()
}