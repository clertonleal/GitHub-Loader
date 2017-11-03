package util

import android.util.Log
import java.io.IOException
import java.io.InputStream

object FileLoader {

    fun loadJSONFromRes(fileName: String): String? {
        val classloader = Thread.currentThread().contextClassLoader
        val input = classloader.getResourceAsStream(fileName)
        return loadJSONFromInputStream(input)
    }

    fun loadJSONFromInputStream(input: InputStream): String? {
        var json: String? = null
        try {
            val size = input.available()
            val buffer = ByteArray(size)
            input.read(buffer)
            input.close()
            json = String(buffer)
        } catch (ex: IOException) {
            Log.e("loadJSONFromAsset", ex.message, ex)
        }

        return json
    }

}