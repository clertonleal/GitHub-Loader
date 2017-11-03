package clertonleal.com.githubloader.service

import android.net.ConnectivityManager

import javax.inject.Inject

class ConnectionService

@Inject
constructor(private val connectivityManager: ConnectivityManager) {
    fun hasInternetConnection(): Boolean {
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
}
