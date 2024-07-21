package com.tahaalangar.googleloginfirebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.tahaalangar.googleloginfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var binding: ActivityMainBinding
    private val db = Firebase.firestore

    companion object{
        lateinit var auth: FirebaseAuth
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth= FirebaseAuth.getInstance()
        val currentUser = auth.currentUser

        if (auth.currentUser==null){
            val intent=Intent(this,SignInActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnMainDataList.setOnClickListener {
            startActivity(Intent(this,DataListActivity::class.java))
        }

        binding.btnMainSignOut.setOnClickListener {
            auth.signOut()
            binding.tvEmail.text=auth.currentUser?.email
            startActivity(Intent(this,SignInActivity::class.java))
            finish()

        }

        binding.btnMainAddData.setOnClickListener {
            val productType = binding.edtMainProductType.text.toString().trim()
            val productCompany = binding.edtMainProductCompanyName.text.toString().trim()
            val productPrice = binding.edtMainProductPrice.text.toString().trim()
            val productStock = binding.edtMainProductStock.text.toString().trim()
            val productLiters = binding.edtMainProductLiters.text.toString().trim()

            if (productType.isNotEmpty() && productCompany.isNotEmpty() &&
                productPrice.isNotEmpty() && productStock.isNotEmpty() && productLiters.isNotEmpty()
            ) {
                val productId = db.collection("products").document().id
                val product = try {
                    Products(
                        id = productId,
                        type = productType,
                        companyName = productCompany,
                        price = productPrice.toDouble(),
                        stock = productStock,
                        liters = productLiters.toDouble()
                    )
                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        this,
                        "Please enter valid numbers for price, stock, and liters",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }

                db.collection("products").document(productId).set(product)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT)
                            .show()
                        binding.edtMainProductType.text.clear()
                        binding.edtMainProductPrice.text.clear()
                        binding.edtMainProductStock.text.clear()
                        binding.edtMainProductLiters.text.clear()
                        binding.edtMainProductCompanyName.text.clear()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(
                            this,
                            "Failed to add product: ${e.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.tvEmail.text=auth.currentUser?.email
    }
}