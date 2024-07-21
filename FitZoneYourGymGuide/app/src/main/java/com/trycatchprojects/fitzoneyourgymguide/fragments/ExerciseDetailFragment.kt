package com.trycatchprojects.fitzoneyourgymguide.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.squareup.picasso.Picasso
import com.trycatchprojects.fitzoneyourgymguide.databinding.FragmentExerciseDetailBinding
import com.trycatchprojects.fitzoneyourgymguide.models.SingleExercisePojoItem
import com.trycatchprojects.fitzoneyourgymguide.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExerciseDetailFragment : Fragment() {
    private lateinit var binding:FragmentExerciseDetailBinding
    private lateinit var exerciseId: String
    private lateinit var exerciseDifficulty: String
    private lateinit var exerciseUrl: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentExerciseDetailBinding.inflate(inflater,container,false)

        exerciseId=arguments?.getString("exercise")!!
        exerciseDifficulty=arguments?.getString("type")!!
        exerciseUrl=arguments?.getString("url")!!

        fetchSingleExercise(exerciseId)
        initializeWebView(exerciseUrl)  // Pass the video URL

        return binding.root
    }

    private fun fetchSingleExercise(exerciseId: String) {

        val call=RetrofitInstance.api.getSingleExercise(exerciseId)
        call.enqueue(object : Callback<List<SingleExercisePojoItem>?> {
            override fun onResponse(
                call: Call<List<SingleExercisePojoItem>?>,
                response: Response<List<SingleExercisePojoItem>?>,
            ) {
                if (response.isSuccessful){
                    val exercise=response.body()?.get(0)
                    binding.tvExerciseDetailName.text = exercise?.name
                    binding.tvExerciseDifficulty.text=exerciseDifficulty
                    binding.tvExerciseDetailTiming.text = exercise?.timimg
                    binding.tvExerciseDetailDescription.text = exercise?.description
                    binding.tvExerciseDetailSteps.text = exercise?.steps
                    binding.tvExerciseDetailMuscleGroup.text = exercise?.exercise_muscles
                    binding.tvExerciseDetailEquipment.text = exercise?.exercise_equipments
                    Picasso.get().load(exercise?.image).into(binding.tvExerciseDetailImage)
                }
            }
            override fun onFailure(call: Call<List<SingleExercisePojoItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    private fun initializeWebView(youtubeVideoId:String) {
        val webView = binding.webView
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.webViewClient = WebViewClient()

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                val url = request?.url.toString()
                if (url.startsWith("https://www.youtube.com/")) {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    startActivity(intent)
                    return true
                }
                return false
            }
        }

        val videoUrl = "https://www.youtube.com/embed/$youtubeVideoId"
        webView.loadUrl(videoUrl)
    }
}

