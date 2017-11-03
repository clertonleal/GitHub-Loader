package clertonleal.com.githubloader.util

import com.google.gson.Gson
import com.google.gson.GsonBuilder

object GsonProvider {
    fun getGson() : Gson {
        return GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").create()
    }
}