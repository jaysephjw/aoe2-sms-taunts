package com.jaywarrick.android.smstaunts

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.provider.Telephony
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * Primary activity; checks for and requests the [Manifest.permission.RECEIVE_SMS] permission.
 */
class MainActivity : AppCompatActivity() {

    var textView: TextView? = null
    var button: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.text)
        button = findViewById(R.id.button)

        button?.setOnClickListener {
            request()
        }
        check()
    }

    fun check(): Boolean {
        val hasReceiveSms = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) == PackageManager.PERMISSION_GRANTED
        return if (!hasReceiveSms) {
            textView?.text = getString(R.string.whoops)
            button?.visibility = View.VISIBLE
            false
        } else {
            textView?.text = getString(R.string.good_to_go)
            button?.visibility = View.INVISIBLE
            true
        }
    }

    fun request() {
        if (!check()) {
            requestPermissions(arrayOf(Manifest.permission.RECEIVE_SMS), 1)
        }
    }

    override fun onRequestPermissionsResult(rc: Int, ps: Array<out String>, results: IntArray) {
        check()
    }
}


/**
 * Listens for incoming SMS and plays supported sounds.
 */
class SmsListener : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (Telephony.Sms.Intents.SMS_RECEIVED_ACTION == intent.action) {
            Telephony.Sms.Intents.getMessagesFromIntent(intent).forEach {
                val smsBody = it.messageBody
                val tauntNumber : Int? = smsBody.toIntOrNull()
                if (tauntNumber in 1..42 || tauntNumber in 104..105) {
                    playSound(context, tauntNumber!!)
                }
            }
        }
    }

    private fun playSound(context: Context, tauntNumber: Int) {
        val player = MediaPlayer()
        player.setDataSource(context.assets.openFd("$tauntNumber.ogg"))
        player.prepare()
        player.start()
    }
}