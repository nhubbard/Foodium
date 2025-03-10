/*
 * MIT License
 *
 * Copyright (c) 2020 Shreyas Patil
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.shreyaspatil.foodium.utils

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET
import androidx.core.content.getSystemService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class NetworkUtils : ConnectivityManager.NetworkCallback() {
    private val _networkState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val networkState: StateFlow<Boolean>
        get() = _networkState

    @SuppressLint("MissingPermission")
    fun getState(context: Context): StateFlow<Boolean> {
        val manager = context.getSystemService<ConnectivityManager>()!!
        manager.registerDefaultNetworkCallback(this)
        var isConnected = false
        for (network in manager.allNetworks) {
            val capability = manager.getNetworkCapabilities(network)
            if (capability != null && capability.hasCapability(NET_CAPABILITY_INTERNET)) {
                isConnected = true
                break
            }
        }
        _networkState.value = isConnected
        return networkState
    }

    override fun onAvailable(network: Network) {
        _networkState.value = true
    }

    override fun onLost(network: Network) {
        _networkState.value = false
    }
}