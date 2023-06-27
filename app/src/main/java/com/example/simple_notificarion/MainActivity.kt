package com.example.simple_notificarion

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    private val CHANNEL_ID = "MY NOTIFICATION"
    private val notificationId = 101
    lateinit var  notifybtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notifybtn = findViewById(R.id.btnnotification)

        creatNotificationChannel()

        notifybtn.setOnClickListener {
            sendNotification()
            val intent = Intent(this,MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }





        }
    }
    private fun creatNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "elvis notification"
            val descriptionText = "this is my notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
                description = descriptionText
            }
            val notificationManager:NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

        }
    }
    @SuppressLint("MissingPermission")
    private fun sendNotification(){
        val pendingIntent: PendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val bitmap = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.bugger)
        val bitmap1 = BitmapFactory.decodeResource(applicationContext.resources,R.drawable.search)

        val  builder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setContentTitle("elvis notification")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentText("you first notification")
            .setContentIntent(pendingIntent)
            .setLargeIcon(bitmap)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap1))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }







    }
}