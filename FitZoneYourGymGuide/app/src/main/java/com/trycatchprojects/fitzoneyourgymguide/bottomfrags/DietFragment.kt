package com.trycatchprojects.fitzoneyourgymguide.bottomfrags

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.trycatchprojects.fitzoneyourgymguide.R
import com.trycatchprojects.fitzoneyourgymguide.adapters.DietListAdapter
import com.trycatchprojects.fitzoneyourgymguide.databinding.FragmentDietBinding
import com.trycatchprojects.fitzoneyourgymguide.models.FoodListPojoItem
import com.trycatchprojects.fitzoneyourgymguide.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DietFragment : Fragment() {
    private lateinit var binding:FragmentDietBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding=FragmentDietBinding.inflate(layoutInflater)

        setUpDietRecyclerView()
        fetchFoodList()
        return binding.root

    }

    private fun fetchFoodList() {
        val call=RetrofitInstance.api.getFoodList()
        call.enqueue(object : Callback<List<FoodListPojoItem>?> {
            override fun onResponse(
                call: Call<List<FoodListPojoItem>?>,
                response: Response<List<FoodListPojoItem>?>,
            ) {
                if (response.isSuccessful){
                    val foodList=response.body()
                    if (foodList!=null){
                        binding.dietRv.adapter=DietListAdapter(foodList){
                            navigateToDetail(it)
                        }
                    }
                }

            }

            override fun onFailure(call: Call<List<FoodListPojoItem>?>, t: Throwable) {
                Toast.makeText(requireContext(), "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun navigateToDetail(it: FoodListPojoItem) {
        val bundle=Bundle()
        bundle.putString("id",it.id)
        findNavController().navigate(R.id.action_dietFragment_to_dietDetailFragment,bundle)

    }

    private fun setUpDietRecyclerView() {
        binding.dietRv.layoutManager=GridLayoutManager(requireContext(),2)
    }
}