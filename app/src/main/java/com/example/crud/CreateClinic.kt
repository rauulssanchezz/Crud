package com.example.crud

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CreateClinic : AppCompatActivity(), CoroutineScope {

    private lateinit var name:EditText
    private lateinit var adress:EditText
    private lateinit var photo:ImageView
    private lateinit var add:Button
    private lateinit var back:Button

    private var url_photo: Uri?=null
    private lateinit var db_ref:DatabaseReference
    private lateinit var st_ref: StorageReference
    private lateinit var clinic_list: MutableList<Clinic>

    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_clinic)

        val this_activity = this
        job = Job()

        name=findViewById(R.id.name)
        adress=findViewById(R.id.adress)
        photo=findViewById(R.id.photo)
        add=findViewById(R.id.add)
        back=findViewById(R.id.back)

        db_ref=FirebaseDatabase.getInstance().getReference()
        st_ref = FirebaseStorage.getInstance().getReference()
        clinic_list=Utilities.getClinicLis(db_ref)

        add.setOnClickListener {
            if (name.text.toString().trim().isEmpty()||adress.text.toString().trim().isEmpty()){
                Toast.makeText(applicationContext,"Faltan datos en el formulario",Toast.LENGTH_SHORT).show()
            }else if(url_photo==null){
                Toast.makeText(applicationContext, "Falta seleccionar la foto", Toast.LENGTH_SHORT
                ).show()
            }else if(Utilities.clinicExist(clinic_list, name.text.toString().trim())){
                Toast.makeText(applicationContext, "Esa clinica ya existe", Toast.LENGTH_SHORT)
                    .show()
            }else{
                var generated_id:String?=db_ref.child("AnimalHealth").child("clinics").push().key
                launch {
                    val url_photo_firebase= Utilities.savePhoto(st_ref,generated_id!!,url_photo!!)

                    Utilities.writeClinic(db_ref,generated_id!!, name.text.toString().trim(),adress.text.toString().trim(),url_photo_firebase)

                    Utilities.toastCourutine(this_activity,applicationContext,"Clinica creada con exito")

                    val activity=Intent(applicationContext,MainActivity::class.java)
                    startActivity(activity)
                }
            }
        }
        back.setOnClickListener {
            val activity=Intent(applicationContext,MainActivity::class.java)
            startActivity(activity)
        }

        photo.setOnClickListener {
            galeryAcces.launch("image/*")
        }
    }
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO + job

    private val galeryAcces = registerForActivityResult(ActivityResultContracts.GetContent()) {
        if(it!=null){
            url_photo = it
            photo.setImageURI(it)
        }
    }
}