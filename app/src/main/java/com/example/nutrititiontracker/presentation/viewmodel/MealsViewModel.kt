package com.example.nutrititiontracker.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.Resource
import com.example.nutrititiontracker.data.repository.MealRepository
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.view.states.MealsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class MealsViewModel(
    private val mealRepository: MealRepository
): ViewModel(), MealsContract.ViewModel {

    override val mealState: MutableLiveData<MealsState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun fetchAllMealsByFirstLetter(c:Char) {
        val subscription = mealRepository
            .fetchMealsByFirstLetter(c)
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

    override fun fetchAllMealsByCategory(categoryName:String) {
        val subscription = mealRepository
            .fetchMealsByCategory(categoryName)
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

    override fun fetchAllMealsByMainIngredient(mainIngredient: String) {
        val subscription = mealRepository
            .fetchMealsByMainIngredient(mainIngredient)
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

    override fun fetchAllMealsByArea(area: String) {
        val subscription = mealRepository
            .fetchMealsByArea(area)
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
    override fun fetchMealByName(name: String) {
        val subscription = mealRepository
            .fetchMealByName(name)
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

    override fun sortAsc() {
        val meals:List<MealResponse> = (mealState.value as MealsState.Success).meals
        val sorted = meals.sortedBy { it.strMeal.toLowerCase(Locale.ROOT) }
        println(sorted)
        mealState.value = MealsState.Success(sorted)
    }

    override fun sortDesc() {
        val meals:List<MealResponse> = (mealState.value as MealsState.Success).meals
        val sorted = meals.sortedByDescending { it.strMeal.toLowerCase(Locale.ROOT) }
        println(sorted)
        mealState.value = MealsState.Success(sorted)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}