package com.example.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    private lateinit var crear: Button
    private lateinit var ver: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        crear = findViewById(R.id.crear)
        ver = findViewById(R.id.ver)

//        ver.setOnClickListener {
//            val activity = Intent(applicationContext, VerClubs::class.java)
//            startActivity(activity)
//        }

        crear.setOnClickListener {
            val activity = Intent(applicationContext, CreateClinic::class.java)
            startActivity(activity)
        }

    }
}