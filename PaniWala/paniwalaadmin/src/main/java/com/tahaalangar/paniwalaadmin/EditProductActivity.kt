package com.tahaalangar.paniwalaadmin

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.squareup.picasso.Picasso
import com.tahaalangar.paniwalaadmin.databinding.ActivityEditProductBinding
import com.tahaalangar.paniwalaadmin.pojos.Products
import java.io.ByteArrayOutputStream

class EditProductActivity : AppCompatActivity() {

    private val db = Firebase.firestore
    private val storage = Firebase.storage
    private lateinit var product: Products
    private lateinit var binding: ActivityEditProductBinding
    private var imageUri: Uri? = null

    private val selectImageLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            imageUri = result.data?.data
            binding.EditImage.setImageURI(imageUri)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityEditProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve the product from the Intent
        product = intent.getSerializableExtra("product") as? Products ?: run {
            Toast.makeText(this, "Failed to load product", Toast.LENGTH_SHORT).show()
            finish() // Close the activity if product is null
            return
        }

        // Populate the fields with the product data
        binding.Editname.setText(product.name)
        binding.Editcategory.setText(product.category)
        binding.Editprice.setText(product.price.toString())
        binding.Editstock.setText(product.stocks.toString())
        binding.EditLiter.setText(product.liter.toString())
        binding.EditMilliLiter.setText(product.milliLiter.toString())

        // Load the existing product image
        if (product.imageUrl.isNotEmpty()) {
            Picasso.get().load(product.imageUrl).into(binding.EditImage)
        } else {
            binding.EditImage.setImageResource(R.drawable.pani_wala_splash) // Default image if none available
        }

        binding.EditImage.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            selectImageLauncher.launch(intent)
        }
        // Update button click listener
        binding.btnEditSave.setOnClickListener {
            val updatedProduct = Products(
                productId = product.productId,
                name = binding.Editname.text.toString(),
                category = binding.Editcategory.text.toString(),
                price = binding.Editprice.text.toString().toDouble(),
                stocks = binding.Editstock.text.toString().toInt(),
                liter = binding.EditLiter.text.toString().toDouble(),
                milliLiter = binding.EditMilliLiter.text.toString().toInt(),
                imageUrl = product.imageUrl // Maintain the current image URL
            )

            if (imageUri != null) {
                uploadImageAndSaveProduct(updatedProduct)
            } else {
                updateProductInFirestore(updatedProduct)
            }
        }
    }


    private fun uploadImageAndSaveProduct(updatedProduct: Products) {
        val storageRef = storage.reference.child("product_images/${updatedProduct.productId}.jpg")

        val baos = ByteArrayOutputStream()
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        storageRef.putBytes(data)
            .addOnSuccessListener {
                storageRef.downloadUrl.addOnSuccessListener { uri ->
                    updatedProduct.imageUrl = uri.toString()
                    updateProductInFirestore(updatedProduct)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to upload image: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateProductInFirestore(product: Products) {
        db.collection("products").document(product.productId).set(product)
            .addOnSuccessListener {
                Toast.makeText(this, "Product updated successfully", Toast.LENGTH_SHORT).show()
                finish() // Close the activity and return to the product list
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to update product: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
     }