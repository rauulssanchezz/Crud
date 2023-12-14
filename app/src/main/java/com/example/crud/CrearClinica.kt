package com.example.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class CrearClinica : AppCompatActivity(), CoroutineScope {

    private lateinit var name:EditText
    private lateinit var adress:EditText
    private lateinit var photo:ImageView
    private lateinit var add:Button
    private lateinit var back:Button

    private lateinit var job: Job
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.crear_club)

        val this_activity = this
        job = Job()

    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job
}