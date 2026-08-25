package com.example.sqlitemigration.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;

public final class ConnectivityMonitor {

    /*
     * Utility class  to check whether the device currently has an active internet connection
     *
     * */

    // USAGE:
    // if (ConnectivityMonitor.isConnected(this)) {
    //     // Load data from server
    // } else {
    //     // Show offline state
    // }

    private ConnectivityMonitor()  {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager manager = context.getSystemService(ConnectivityManager.class);
        if (manager == null) {
            return false;
        }
        Network network = manager.getActiveNetwork();
        if (network == null)
            return false;
        NetworkCapabilities capabilities = manager.getNetworkCapabilities(network);
        return capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET);
    }
}
