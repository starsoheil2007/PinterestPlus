package com.soulvana.pinterestplus.component

import android.graphics.BitmapFactory
import android.webkit.DownloadListener
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.soulvana.filedownloader.DownloadFileListener
import com.soulvana.pinterestplus.PinterestApplication
import java.io.ByteArrayOutputStream
import android.graphics.Bitmap


/**
 * set adapter to RecyclerView in data binding
 *
 * @param view RecyclerView
 * @param adapter Adapter of RecyclerView
 */
@BindingAdapter("adapter")
fun setAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.adapter = adapter
}

/**
 * show normal image to imageview
 *
 * @param view image view
 * @param imageUrl full url of image
 */
@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    var downloadFile =
        PinterestApplication.getImageCashingLibrary().DownloadFile(imageUrl, object : DownloadFileListener {
            override fun onStartDownload() {
            }

            override fun onDownloading() {

            }

            override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
                val bitmap =
                    BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().size)
                view.setImageBitmap(bitmap)
                view.requestLayout()
            }

            override fun onDownloadNothing() {
            }

            override fun onConnectionError() {

            }
        })

    downloadFile.cancelDownload()
}


/**
 * show normal image to imageview
 *
 * @param view image view
 * @param imageResource image R
 */
@BindingAdapter("imageResource")
fun loadImageResouce(view: ImageView, imageResource: Int) {
    view.setImageResource(imageResource)
}
