package com.example.spectrogrammer34

import android.Manifest.permission
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

val PERMISSION_REQUEST_CODE: Int = 200

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        if (ContextCompat.checkSelfPermission(
                this,
                permission.RECORD_AUDIO
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>(permission.RECORD_AUDIO),
                PERMISSION_REQUEST_CODE
            )
        }


        /*
        try {
            Class.forName("dalvik.system.CloseGuard")
                    .getMethod("setEnabled", boolean.class)
                    .invoke(null, true);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
        */
        val buttonListener = View.OnClickListener { v ->
            when (v.id) {
                R.id.fft_activity -> {

                    val intent = Intent(
                        this@MainActivity,
                        WaterfallApp::class.java
                    )
                    intent.putExtra(WaterfallApp.EXTRA_MODE, "FFT")
                    startActivity(intent)

                }

                R.id.music_activity -> {

                    val intent = Intent(
                        this@MainActivity,
                        WaterfallApp::class.java
                    )
                    intent.putExtra(WaterfallApp.EXTRA_MODE, "Music")
                    startActivity(intent)

                }

            }
        }
        val fft_activity = findViewById<View>(R.id.fft_activity) as Button
        fft_activity.setOnClickListener(buttonListener)
        val music_activity = findViewById<View>(R.id.music_activity) as Button
        music_activity.setOnClickListener(buttonListener)

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> if (grantResults.isNotEmpty()) {
                val recordingAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED
            }
        }
    }

}