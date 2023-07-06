package com.example.nutrititiontracker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.Resource
import com.example.nutrititiontracker.data.repository.MealRepository
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.MealsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MealsViewModel(
    private val mealRepository: MealRepository
): ViewModel(), MealsContract.ViewModel {

    override val mealState: MutableLiveData<MealsState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun fetchAllMealsByFirstLetter() {
        val subscription = mealRepository
            .fetchMealsByFirstLetter('a')
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it) {
                    is Resource.Loading -> mealState.value = MealsState.Loading
                    is Resource.Success -> mealState.value = MealsState.Success(it.data)
                    is Resource.Error -> mealState.value = MealsState.Error("Error happened while fetching data from the server")
                }
            },{
                mealState.value = MealsState.Error("Error happened while fetching data from the server")
            })
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}