package com.islamzada.cryptotrackerapp.data.notification

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class NotificationService : Service() {

    private val TAG = "Custom service" // Define a constant string for logging purposes.

    init {
        Log.d(TAG, "Service is running...") // Initialize block: This code will run when an instance of NotificationService is created.
    }

    override fun stopService(name: Intent?): Boolean {
        return super.stopService(name) // Override the stopService() method from the Service class.
    }

    override fun onBind(p0: Intent?): IBinder? = null // Override the onBind() method from the Service class.

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Override the onStartCommand() method from the Service class.
        // This method is called when the service is started.

        return START_STICKY // Return START_STICKY to indicate that the service should be restarted if it gets killed.
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "Service cancelled...") // Override the onDestroy() method from the Service class.
        // This method is called when the service is about to be destroyed.
    }
}