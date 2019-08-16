package com.vivekvishwanath.ah_sprint2

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

// Step 1: Make the Puppy model
// Step 2: Process raw puppy data
// Step 3: Design RV item layout
// Step 4: Created adapter for RecyclerView
// Step 5: Finish RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        PuppyRepository.createPuppyList()

        puppy_list_view.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PuppyListAdapter(PuppyRepository.puppyList)
        }

        favorites_button.setOnClickListener {
            createNotification(getFavorites())
        }
    }

    fun getFavorites(): String {
        var favoritesString = ""
        for (puppy in PuppyRepository.puppyList) {
            if (puppy.isFavorite) favoritesString += "${puppy.breed}, "
        }

        return favoritesString
    }

    fun createNotification(favorites: String) {
        val channelId = "${this.packageName}.simplechannel"
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Puppy Notification Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "Channel to send puppy notification"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setPriority(NotificationManager.IMPORTANCE_HIGH)
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Puppy Notification")
                .setContentText(favorites)
                .setAutoCancel(true)
        notificationManager.notify(1, notificationBuilder.build())
    }
}
