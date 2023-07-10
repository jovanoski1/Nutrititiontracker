package com.example.nutrititiontracker.presentation.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealType
import com.example.nutrititiontracker.databinding.FragmentMealPlanBinding
import com.example.nutrititiontracker.presentation.view.recycler.adapter.PlanGridAdapter
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import java.util.*

class MealPlanFragment : Fragment() {

    private var _binding: FragmentMealPlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PlanGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMealPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
    }

    private fun initUi() {
        binding.listRv.layoutManager = GridLayoutManager(context, 4)

        adapter = PlanGridAdapter(object : MyMealClickListener {

            override fun onEditClick(mealEntity: MealEntity) {
            }

            override fun onDeleteClick(mealEntity: MealEntity) {
            }
        })

        binding.listRv.adapter = adapter

        val mealEntities = mutableListOf<MealEntity>()

        for (i in 0 until 28) {
            val mealEntity = MealEntity(
                name = i.toString(),
                category = null,
                plannedDate = Date(),
                mealType = MealType.BREAKFAST, // Replace with the appropriate meal type
                image = "https://www.themealdb.com/images/media/meals/sypxpx1515365095.jpg",
                instructions = null,
                urlToVideo = null,
                strIngredient1 = null,
                strIngredient2 = null,
                strIngredient3 = null,
                strIngredient4 = null,
                strIngredient5 = null,
                strIngredient6 = null,
                strIngredient7 = null,
                strIngredient8 = null,
                strIngredient9 = null,
                strIngredient10 = null,
                strIngredient11 = null,
                strIngredient12 = null,
                strIngredient13 = null,
                strIngredient14 = null,
                strIngredient15 = null,
                strIngredient16 = null,
                strIngredient17 = null,
                strIngredient18 = null,
                strIngredient19 = null,
                strIngredient20 = null,
                strMeasure1 = null,
                strMeasure2 = null,
                strMeasure3 = null,
                strMeasure4 = null,
                strMeasure5 = null,
                strMeasure6 = null,
                strMeasure7 = null,
                strMeasure8 = null,
                strMeasure9 = null,
                strMeasure10 = null,
                strMeasure11 = null,
                strMeasure12 = null,
                strMeasure13 = null,
                strMeasure14 = null,
                strMeasure15 = null,
                strMeasure16 = null,
                strMeasure17 = null,
                strMeasure18 = null,
                strMeasure19 = null,
                strMeasure20 = null,
                userId = 0 // Replace with the appropriate user ID
            )
            mealEntities.add(mealEntity)
        }

        adapter.submitList(mealEntities)
    }

}