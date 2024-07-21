package com.trycatchprojects.fitzoneyourgymguide.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trycatchprojects.fitzoneyourgymguide.databinding.ItemViewDietDesignBinding
import com.trycatchprojects.fitzoneyourgymguide.models.FoodListPojoItem

class DietListAdapter (private var foodListPojo: List<FoodListPojoItem>,
                       private val onItemClicked: (FoodListPojoItem) -> Unit
): RecyclerView.Adapter<DietListAdapter.ViewHolder>(){
    class ViewHolder (val binding: ItemViewDietDesignBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemViewDietDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return foodListPojo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val foods=foodListPojo[position]
        holder.binding.apply {
            dietName.text=foods.name
            dietCalorie.text="${foods.calories} Kcal"
            dietProteins.text="${foods.protein} gms"
            Picasso.get().load(foods.image).into(dietImg)
        }
        holder.itemView.setOnClickListener {
            onItemClicked(foods)
        }


    }
}