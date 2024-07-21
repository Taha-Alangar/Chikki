package com.trycatchprojects.fitzoneyourgymguide.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import com.trycatchprojects.fitzoneyourgymguide.databinding.FragmentDietDetailBinding
import com.trycatchprojects.fitzoneyourgymguide.models.SingleFoodPojoItem
import com.trycatchprojects.fitzoneyourgymguide.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DietDetailFragment : Fragment() {
    private lateinit var binding:FragmentDietDetailBinding
    private lateinit var dietId:String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentDietDetailBinding.inflate(layoutInflater)

        dietId = arguments?.getString("id") ?: ""

        fetchSingleDiet(dietId)

        return binding.root
    }

    private fun fetchSingleDiet(dietId: String) {
        val call= RetrofitInstance.api.getSingleFood(dietId)
        call.enqueue(object : Callback<List<SingleFoodPojoItem>?> {
            override fun onResponse(
                call: Call<List<SingleFoodPojoItem>?>,
                response: Response<List<SingleFoodPojoItem>?>,
            ) {
                if (response.isSuccessful) {
                    val data = response.body()

                    if (data != null) {
                        val singleFood = data[0] // Get the first item in the list
                        binding.dietDetailName.text = singleFood.name
                        binding.dietDetailSodium.text = singleFood.sodium.toString()
                        binding.dietDetailCalories.text = "${singleFood.calories} Kcal"
                        binding.dietDetailDescription.text = singleFood.description
                        Picasso.get().load(singleFood.image).into(binding.dietDetailImg)


                        // Set fat count and progress
                        val fats = singleFood.fats.toIntOrNull() ?: 0
                        binding.dietDetailFats.text = "$fats g"
                        binding.progressBarFats.progress = fats

                        // Adjust the position of dietDetailFats TextView
                        binding.progressBarFats.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                            override fun onGlobalLayout() {
                                binding.progressBarFats.viewTreeObserver.removeOnGlobalLayoutListener(this)
                                val progressBarWidth = binding.progressBarFats.width
                                val progressBarProgress = binding.progressBarFats.progress
                                val progressRatio = progressBarProgress / 100f
                                val textViewWidth = binding.dietDetailFats.width

                                // Set the left margin of the TextView dynamically
                                val params = binding.dietDetailFats.layoutParams as ConstraintLayout.LayoutParams
                                params.marginStart = (progressBarWidth * progressRatio - textViewWidth / 2).toInt()
                                binding.dietDetailFats.layoutParams = params
                            }
                        })

                        // Set carbs count and progress
                        val carbs = singleFood.carbs.toIntOrNull() ?: 0
                        binding.dietDetailCarbs.text = "$carbs g"
                        binding.progressBarCarbs.progress = carbs

                        // Adjust the position of dietDetailFats TextView
                        binding.progressBarCarbs.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                            override fun onGlobalLayout() {
                                binding.progressBarCarbs.viewTreeObserver.removeOnGlobalLayoutListener(this)
                                val progressBarWidth = binding.progressBarCarbs.width
                                val progressBarProgress = binding.progressBarCarbs.progress
                                val progressRatio = progressBarProgress / 100f
                                val textViewWidth = binding.dietDetailCarbs.width

                                // Set the left margin of the TextView dynamically
                                val params = binding.dietDetailCarbs.layoutParams as ConstraintLayout.LayoutParams
                                params.marginStart = (progressBarWidth * progressRatio - textViewWidth / 2).toInt()
                                binding.dietDetailCarbs.layoutParams = params
                            }
                        })


                        // Set carbs count and progress
                        val protein = singleFood.protein.toIntOrNull() ?: 0
                        binding.dietDetailProtein.text = "$protein g"
                        binding.progressBarProtein.progress = protein

                        // Adjust the position of dietDetailFats TextView
                        binding.progressBarProtein.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                            override fun onGlobalLayout() {
                                binding.progressBarProtein.viewTreeObserver.removeOnGlobalLayoutListener(this)
                                val progressBarWidth = binding.progressBarProtein.width
                                val progressBarProgress = binding.progressBarProtein.progress
                                val progressRatio = progressBarProgress / 100f
                                val textViewWidth = binding.dietDetailProtein.width

                                // Set the left margin of the TextView dynamically
                                val params = binding.dietDetailProtein.layoutParams as ConstraintLayout.LayoutParams
                                params.marginStart = (progressBarWidth * progressRatio - textViewWidth / 2).toInt()
                                binding.dietDetailProtein.layoutParams = params
                            }
                        })

                    }
                }
            }

            override fun onFailure(call: Call<List<SingleFoodPojoItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

}