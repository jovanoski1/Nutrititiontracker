package com.example.nutrititiontracker.presentation.view.dialogs

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ToggleButton
import androidx.core.view.isVisible
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.databinding.MealPlanDialogBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.recycler.adapter.MealAdapter
import com.example.nutrititiontracker.presentation.view.recycler.adapter.MyMealAdapter
import com.example.nutrititiontracker.presentation.view.recycler.adapter.MyMealPlanAdapter
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MealPlanMealClickListener
import com.example.nutrititiontracker.presentation.view.recycler.listeners.MyMealClickListener
import com.example.nutrititiontracker.presentation.view.states.MealsState
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MealPlanDialog(
    private val mealPlanMealClickListener: MealPlanMealClickListener
) : DialogFragment() {

    private var _binding: MealPlanDialogBinding? = null
    private val binding get() = _binding!!

    private val mealsViewModel: MealsContract.ViewModel by viewModel<MealsViewModel>()
    private lateinit var myMealAdapter: MyMealPlanAdapter
    private lateinit var mealAdapter: MealAdapter
    private val sharedPreferences: SharedPreferences by inject()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MealPlanDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealsViewModel.getMealsByNameForUser("", sharedPreferences.getLong("userId", -1))

        initUi()
        initListeners()
        initObservers()
    }

    private fun initObservers() {

        mealsViewModel.mealState.observe(this, Observer {
            when(it){
                is MealsState.Success ->{
                    mealAdapter.submitList(it.meals)
                    binding.loadingPbMl.isVisible = false
                    binding.listRv.isVisible = true
                }
                is MealsState.Loading ->{
                    binding.loadingPbMl.isVisible = true
                    binding.listRv.isVisible = false
                }
                else -> {
                    binding.loadingPbMl.isVisible = false
                    binding.listRv.isVisible = true
                }
            }
        })

        mealsViewModel.mealsForUser.observe(this, Observer {
            myMealAdapter.submitList(it)
        })
    }

    private fun initListeners() {
        binding.toggleButton.setOnClickListener {
            if ((it as ToggleButton).isChecked){
                binding.listRv.adapter = mealAdapter
            }
            else{
                binding.listRv.adapter = myMealAdapter
            }
        }

        binding.searchEt.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                if (binding.toggleButton.isChecked){
                    mealsViewModel.fetchMealByName(p0.toString())
                }
                else{
                    mealsViewModel.getMealsByNameForUser(p0.toString(), sharedPreferences.getLong("userId", -1))
                }
            }

        })
    }

    private fun initUi() {
        binding.listRv.layoutManager = LinearLayoutManager(context)

        myMealAdapter = MyMealPlanAdapter(object : MealPlanMealClickListener {
            override fun onMyMealClick(mealEntity: MealEntity) {
                println(mealEntity.name)
                mealPlanMealClickListener.onMyMealClick(mealEntity)
                dialog?.dismiss()
            }

            override fun onMealClick(mealResponse: MealResponse) {
            }

        })

        mealAdapter = MealAdapter(object : MealClickListener {
            override fun onItemClick(mealResponse: MealResponse) {
                println(mealResponse.strMeal)
                mealPlanMealClickListener.onMealClick(mealResponse)
                dialog?.dismiss()
            }
        })

        binding.listRv.adapter = myMealAdapter
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.40).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}