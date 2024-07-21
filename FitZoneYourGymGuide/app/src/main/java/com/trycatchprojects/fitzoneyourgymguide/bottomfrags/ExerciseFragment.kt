package com.trycatchprojects.fitzoneyourgymguide.bottomfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.trycatchprojects.fitzoneyourgymguide.R
import com.trycatchprojects.fitzoneyourgymguide.adapters.ExerciseListAdapter
import com.trycatchprojects.fitzoneyourgymguide.databinding.FragmentExerciseBinding
import com.trycatchprojects.fitzoneyourgymguide.models.AllExerciseByCategoryPojoItem
import com.trycatchprojects.fitzoneyourgymguide.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExerciseFragment : Fragment() {
    private lateinit var binding:FragmentExerciseBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentExerciseBinding.inflate(layoutInflater)

        setExerciseUpRecyclerView()
        fetchExerciseListData()

        return binding.root
    }

    private fun fetchExerciseListData() {
        val call= RetrofitInstance.api.getAllExerciseByCategory("1,2,3")
        call.enqueue(object : Callback<List<AllExerciseByCategoryPojoItem>?> {
            override fun onResponse(
                call: Call<List<AllExerciseByCategoryPojoItem>?>,
                response: Response<List<AllExerciseByCategoryPojoItem>?>,
            ) {
                if (response.isSuccessful){
                    val list=response.body()
                    if (list!=null){
                        binding.exerciseRv.adapter=ExerciseListAdapter(list){
                            navigateToExerciseDetail(it)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<AllExerciseByCategoryPojoItem>?>, t: Throwable) {

                Toast.makeText(requireContext(),t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToExerciseDetail(it: AllExerciseByCategoryPojoItem) {
        val bundle=Bundle()
        bundle.putString("exercise",it.id)
        bundle.putString("type",it.cat_difficulty)
        bundle.putString("url",it.url)
        findNavController().navigate(R.id.action_exerciseFragment_to_exerciseDetailFragment,bundle)
    }

    private fun setExerciseUpRecyclerView() {
        binding.exerciseRv.layoutManager= LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
    }
}