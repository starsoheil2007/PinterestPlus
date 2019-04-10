package com.soulvana.pinterestplus

import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.soulvana.filedownloader.DownloadFileListener
import com.soulvana.filedownloader.FileDownloader

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.io.ByteArrayOutputStream

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    companion object {
        const val SAMPLE_IMAGE_01 = "http://soheil-tayyeb.com/sample_images/image01.jpg"
        const val SAMPLE_IMAGE_02 = "http://soheil-tayyeb.com/sample_images/image02.jpeg"
        const val SAMPLE_IMAGE_03 = "http://soheil-tayyeb.com/sample_images/image03.jpeg"
        const val SAMPLE_IMAGE_04 = "http://soheil-tayyeb.com/sample_images/image04.jpeg"
        const val SAMPLE_IMAGE_05 = "http://soheil-tayyeb.com/sample_images/image05.jpg"
        const val SAMPLE_IMAGE_06 = "http://soheil-tayyeb.com/sample_images/image06.jpg"


    }

    fun getFilDownloaderInstance() {

    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.soulvana.pinterestplus", appContext.packageName)
    }

    @Test
    fun testStartDownload() {
        var numberOfCacheImage = 1
        FileDownloader.initLibrary(numberOfCacheImage).DownloadFile(SAMPLE_IMAGE_01, object : DownloadFileListener {
            override fun onStartDownload() {

            }

            override fun onDownloading() {
            }

            override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
                assertNotNull(outputStream)
            }

            override fun onDownloadNothing() {
            }

            override fun onConnectionError() {
            }
        })
    }

    @Test
    fun testFileDownloaderEvictCorrectFileFromMemory() {
        var numberOfCacheImage = 3
        var fileDownloader = FileDownloader.initLibrary(numberOfCacheImage)

        fileDownloader.DownloadFile(SAMPLE_IMAGE_01, object : DownloadFileListener {
            override fun onStartDownload() {
            }

            override fun onDownloading() {
            }

            override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
                assertNotNull(outputStream)
            }

            override fun onDownloadNothing() {
            }

            override fun onConnectionError() {
            }
        })


        fileDownloader.DownloadFile(SAMPLE_IMAGE_02, object : DownloadFileListener {
            override fun onStartDownload() {
            }

            override fun onDownloading() {
            }

            override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
                assertNotNull(outputStream)
            }

            override fun onDownloadNothing() {
            }

            override fun onConnectionError() {
            }
        })


        fileDownloader.DownloadFile(SAMPLE_IMAGE_03, object : DownloadFileListener {
            override fun onStartDownload() {
            }

            override fun onDownloading() {
            }

            override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
                assertNotNull(outputStream)
            }

            override fun onDownloadNothing() {
            }

            override fun onConnectionError() {
            }
        })

        //Now We have 3 file in memory
        assertEquals(fileDownloader.getMemoryCacheSize(), 3)

        //Now request again file one
        fileDownloader.DownloadFile(SAMPLE_IMAGE_01, object : DownloadFileListener {
            override fun onStartDownload() {
            }

            override fun onDownloading() {
            }

            override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
                assertNotNull(outputStream)
            }

            override fun onDownloadNothing() {
            }

            override fun onConnectionError() {
            }
        })

        //now request again file 3
        fileDownloader.DownloadFile(SAMPLE_IMAGE_03, object : DownloadFileListener {
            override fun onStartDownload() {
            }

            override fun onDownloading() {
            }

            override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
                assertNotNull(outputStream)
            }

            override fun onDownloadNothing() {
            }

            override fun onConnectionError() {
            }
        })

        //now request new file
        fileDownloader.DownloadFile(SAMPLE_IMAGE_04, object : DownloadFileListener {
            override fun onStartDownload() {
            }

            override fun onDownloading() {
            }

            override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
                assertNotNull(outputStream)
            }

            override fun onDownloadNothing() {
            }

            override fun onConnectionError() {
            }
        })

        //Now We have 3 file in memory
        assertEquals(fileDownloader.getMemoryCacheSize(), 3)

        //Now must be file 2 isn't in memory
        assertEquals(fileDownloader.checkIsInMemory(SAMPLE_IMAGE_02), false)
    }


}
