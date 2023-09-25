package com.example.pigolevmyapplication.viewmodel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.internal.bind.TypeAdapters.URL
import java.net.URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class DetailsFragmentViewModel {

    companion object {
        suspend fun loadWallpaper(url: String): Bitmap {
            return suspendCoroutine {
                val url = URL(url)
                val bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                it.resume(bitmap)
            }
        }
    }

}