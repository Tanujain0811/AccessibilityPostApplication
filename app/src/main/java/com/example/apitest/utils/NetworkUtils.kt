package com.example.apitest.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi

class NetworkUtils {
    companion object {
        fun isInternetAvailable(context: Context): Boolean {
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
                if (Build.VERSION.SDK_INT == VERSION_CODES.M) {
                    return this.getNetworkCapabilities(this.activeNetwork)
                        ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
                } else {
                    @Suppress("DEPRECIATION")
                    return this.activeNetworkInfo?.isConnected ?: false
                }
            }
        }
    }

}