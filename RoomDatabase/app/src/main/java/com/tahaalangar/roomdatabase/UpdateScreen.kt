package com.tahaalangar.roomdatabase

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateScreen : AppCompatActivity() {
    lateinit var nameET:EditText
    lateinit var weightET:EditText
    lateinit var priceET:EditText


    lateinit var updateBtn:Button
    lateinit var contact: Contact


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_screen)

        nameET=findViewById(R.id.editTextFruitsNameUpdate)
        weightET=findViewById(R.id.editTextFruitsWeightUpdate)
        priceET=findViewById(R.id.editTextFruitsPriceUpdate)

        updateBtn=findViewById(R.id.buttonUpdate)


        contact=intent.getSerializableExtra("model")as Contact

        nameET.setText(contact.name)
        weightET.setText(contact.weight)
        priceET.setText(contact.price)


        updateBtn.setOnClickListener {
            val contact=Contact(contact.id,nameET.text.toString(),weightET.text.toString()
                ,priceET.text.toString())

            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getDatabase(this@UpdateScreen).userDao().update(contact)
                runOnUiThread {
                    finish()
                }
            }
        }


    }
    companion object {
        private const val REQUEST_CODE_SELECT_IMAGE = 100
    }
}