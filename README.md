# Pinterst Plus Application

This app sample application to introduce a library called 'File Downloader' to download files and images.  

This library and project has written by Kotlin.

## Getting Started

Before You do anything . Please know this app is for test

1 - Add module to your application and add in app/build.gradle like this :

```
 implementation project(':filedownloader')
```

2 - In your main ApplicationClass create new instance of file downloader library :

```
 companion object {
         const val NUMBER_OF_FILE_IN_CACHE = 5
         @SuppressLint("StaticFieldLeak")
         private lateinit var fileDownloaderLibrary: FileDownloader
 
         fun getImageCashingLibrary(): FileDownloader {
             return fileDownloaderLibrary
         }
     }
     
     
      override fun onCreate() {
             super.onCreate()
             fileDownloaderLibrary = FileDownloader.initLibrary(NUMBER_OF_FILE_IN_CACHE)
     
       }
     
```  

The static variable NUMBER_OF_FILE_IN_CACHE is for config number of file must be saved in memory.

3 - Now every time you want download a file or image you can use this :

 ```

 PinterestApplication.getImageCashingLibrary().DownloadFile(imageUrl, object : DownloadFileListener {
        override fun onStartDownload() {
        }

        override fun onDownloading() {

        }

        override fun onFinishDownload(outputStream: ByteArrayOutputStream) {
            
        }

        override fun onDownloadNothing() {
        }

        override fun onConnectionError() {

        }
    })
    
 ```
 
 If you need image you must convert  ByteArrayOutputStream to bitmap :
 (From on onFinishDownload)
 
 
 ```
        val bitmap = BitmapFactory.decodeByteArray(outputStream.toByteArray(), 0, outputStream.toByteArray().size)
        imageView.setImageBitmap(bitmap)
 ```
 
 
### Library File Test Class

I wrote test library classes in this file :

You can run all test and see comments

```
ExampleInstrumentedTest.kt
```


### Library File Descriptions

```
DownloadFileListener.kt
```

Interface of event download status


```
DownloadingFileModel.kt
```

Model of file in memory that save (file stream,download status, last date used, listener list of user, task of download)


```
FileDownloader.kt
```

Main file download init class


```
FileDownloaderTask.kt
```

Async Task of download file in background



```
MemoryCache.kt
```

Manage downloaded file in memory 



## Cancel download

You can save instance of each download and cancel that every time you want :

 ```

 var file - PinterestApplication.getImageCashingLibrary().DownloadFile(imageUrl, object : DownloadFileListener {...})
 
 file..cancelDownload()
     
 ```
 
### Last note

This is a test for company to hire me . 
