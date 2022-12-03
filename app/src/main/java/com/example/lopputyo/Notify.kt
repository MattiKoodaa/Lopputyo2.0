package com.example.lopputyo

import android.app.*
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.lopputyo.databinding.ActivityNotifBinding
import java.util.*

class Notify : AppCompatActivity()
{
    private lateinit var binding : ActivityNotifBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotifBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        binding.submitButton.setOnClickListener { scheduleNotification() }
        binding.apply {
            toggle = ActionBarDrawerToggle(this@Notify, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()


            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.firstItem -> {
                        Toast.makeText(this@Notify, "Avataan muistio", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Notify, MainActivity::class.java)
                        startActivity(intent)
                    }
                    R.id.secondItem -> {
                        Toast.makeText(this@Notify, "Avataan valmiit", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@Notify, Done::class.java)
                        startActivity(intent)
                    }
                    R.id.thirdItem -> {
                        Toast.makeText(this@Notify, "Avataan ilmoitukset", Toast.LENGTH_SHORT)
                            .show()
                        val intent = Intent(this@Notify, Notify::class.java)
                        startActivity(intent)
                    }
                }
                true
            }
        }
        }
   private fun scheduleNotification()
    {
        val intent = Intent(applicationContext, Notification::class.java)
        var title = binding.titleET.text.toString()
        if(title.isEmpty()){
            title = "Muista:"
        }
        val message = binding.messageET.text.toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val time = getTime()
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            time,
            pendingIntent
        )
        showAlert(time, title, message)
    }

    private fun showAlert(time: Long, title: String, message: String)
    {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle("Muistutus lisätty")
            .setMessage(
                "Otsikko: " + title +
                        "\nViesti: " + message +
                        "\nKello: " + dateFormat.format(date) + " " + timeFormat.format(date))
            .setPositiveButton("Ok"){_,_ ->}
            .show()
    }

    private fun getTime(): Long
    {
        val minute = binding.timePicker.minute
        val hour = binding.timePicker.hour
        val day = binding.datePicker.dayOfMonth
        val month = binding.datePicker.month
        val year = binding.datePicker.year

        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }


    private fun createNotificationChannel()
    {
        val name = "Notify Channel"
        val desc = "A Description of the Channel"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}





