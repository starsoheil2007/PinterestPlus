package com.soulvana.pinterestplus

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.soulvana.filedownloader.FileDownloader

class PinterestApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        fileDownloaderLibrary = FileDownloader.initLibrary(NUMBER_OF_FILE_IN_CACHE)

    }


    companion object {
        const val NUMBER_OF_FILE_IN_CACHE = 5
        @SuppressLint("StaticFieldLeak")
        private lateinit var fileDownloaderLibrary: FileDownloader

        fun getImageCashingLibrary(): FileDownloader {
            return fileDownloaderLibrary
        }
    }
}