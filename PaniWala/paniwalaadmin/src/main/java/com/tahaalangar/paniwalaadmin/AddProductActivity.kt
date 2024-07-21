package com.tahaalangar.paniwalaadmin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import com.tahaalangar.paniwalaadmin.databinding.ActivityAddProductBinding
import com.tahaalangar.paniwalaadmin.pojos.Products
import id.zelory.compressor.Compressor
import id.zelory.compressor.constraint.format
import id.zelory.compressor.constraint.quality
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding
    private val db = Firebase.firestore
    private val storage = Firebase.storage
    private lateinit var auth: FirebaseAuth
    private var imageUri: Uri? = null


    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            result.data?.data?.let { uri ->
                imageUri = uri
                binding.productImage.setImageURI(imageUri)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth= FirebaseAuth.getInstance()

        binding.addProductImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            selectImageLauncher.launch(intent)
        }


        binding.btnMainAddData.setOnClickListener {
            val productName = binding.edtMainProductName.text.toString().trim()
            val productCategory = binding.edtMainProductCategory.text.toString().trim()
            val productPrice = binding.edtMainProductPrice.text.toString().trim()
            val productStock = binding.edtMainProductStock.text.toString().trim()
            val productLiter = binding.edtMainProductLiter.text.toString().trim()
            val productMilliLiter = binding.edtMainProductMilliLiter.text.toString().trim()

            if (productName.isNotEmpty() && productCategory.isNotEmpty() &&
                productPrice.isNotEmpty() && productStock.isNotEmpty() && productLiter.isNotEmpty()&& productMilliLiter.isNotEmpty()
            ) {
                imageUri?.let { uri ->
                    lifecycleScope.launch {
                        uploadImageAndSaveProduct(
                            productName,
                            productCategory,
                            productPrice.toDouble(),
                            productStock.toInt(),
                            productLiter.toDouble(),
                            productMilliLiter.toInt(),
                            uri
                        )
                    }
                } ?: run {
                    Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadImageAndSaveProduct(name: String, category: String, price: Double, stock: Int, liter: Double, milliLiter: Int ,imageUri: Uri) {
        val productId = db.collection("products").document().id
        val storageRef = storage.reference.child("product_images/$productId.jpg")

        try {
            val inputStream = contentResolver.openInputStream(imageUri)
            val data = getBytes(inputStream)

            storageRef.putBytes(data)
                .addOnSuccessListener { _ ->
                    storageRef.downloadUrl.addOnSuccessListener { uri ->
                        val product = Products(
                            productId = productId,
                            name = name,
                            category = category,
                            price = price,
                            stocks = stock,
                            liter = liter,
                            milliLiter = milliLiter,
                            imageUrl = uri.toString() // Store the image URL
                        )

                        db.collection("products").document(productId).set(product)
                            .addOnSuccessListener {
                                Toast.makeText(this@AddProductActivity, "Product added successfully", Toast.LENGTH_SHORT).show()
                                clearFields()
                            }
                            .addOnFailureListener { e ->
                                Log.e("AddProductActivity", "Failed to add product: ${e.message}")
                                Toast.makeText(this@AddProductActivity, "Failed to add product: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    }
                }
                .addOnFailureListener { e ->
                    Log.e("AddProductActivity", "Failed to upload image: ${e.message}")
                    Toast.makeText(this@AddProductActivity, "Failed to upload image: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        } catch (e: Exception) {
            Log.e("AddProductActivity", "Error uploading image: ${e.message}")
            Toast.makeText(this@AddProductActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }


    private fun getBytes(inputStream: InputStream?): ByteArray {
        val byteBuffer = ByteArrayOutputStream()
        val bufferSize = 1024
        val buffer = ByteArray(bufferSize)

        var len = inputStream?.read(buffer) ?: -1
        while (len != -1) {
            byteBuffer.write(buffer, 0, len)
            len = inputStream?.read(buffer) ?: -1
        }

        return byteBuffer.toByteArray()
    }

    private fun clearFields() {
        binding.edtMainProductName.text.clear()
        binding.edtMainProductPrice.text.clear()
        binding.edtMainProductStock.text.clear()
        binding.edtMainProductLiter.text.clear()
        binding.edtMainProductMilliLiter.text.clear()
        binding.edtMainProductCategory.text.clear()
        binding.productImage.setImageResource(R.drawable.pani_wala_splash)
    }
}