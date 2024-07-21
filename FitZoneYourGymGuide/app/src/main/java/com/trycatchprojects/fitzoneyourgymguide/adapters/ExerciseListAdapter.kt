package com.trycatchprojects.fitzoneyourgymguide.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trycatchprojects.fitzoneyourgymguide.databinding.ItemViewDietDesignBinding
import com.trycatchprojects.fitzoneyourgymguide.databinding.ItemViewExerciseBinding
import com.trycatchprojects.fitzoneyourgymguide.models.AllExerciseByCategoryPojoItem
import com.trycatchprojects.fitzoneyourgymguide.models.FoodListPojoItem

class ExerciseListAdapter (private var exerciseListPojo: List<AllExerciseByCategoryPojoItem>,
                            private val onItemClicked: (AllExerciseByCategoryPojoItem) -> Unit
): RecyclerView.Adapter<ExerciseListAdapter.ViewHolder>(){
    class ViewHolder (val binding: ItemViewExerciseBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemViewExerciseBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return exerciseListPojo.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val exercise=exerciseListPojo[position]
        holder.binding.apply {
            exerciseName.text=exercise.name
            exerciseDifficulty.text=exercise.cat_difficulty
            Picasso.get().load(exercise.image).into(exerciseImg)
        }
        holder.itemView.setOnClickListener {
            onItemClicked(exercise)
        }
    }
}