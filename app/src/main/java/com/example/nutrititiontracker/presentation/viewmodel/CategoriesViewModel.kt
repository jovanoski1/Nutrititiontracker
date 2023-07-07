package com.example.nutrititiontracker.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrititiontracker.data.models.Resource
import com.example.nutrititiontracker.data.repository.CategoryRepository
import com.example.nutrititiontracker.presentation.contract.CategoriesContract
import com.example.nutrititiontracker.presentation.view.states.AreasState
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import com.example.nutrititiontracker.presentation.view.states.IngredientsState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CategoriesViewModel(
    private val categoryRepository: CategoryRepository
): ViewModel(), CategoriesContract.ViewModel {

    override val categoriesState: MutableLiveData<CategoriesState> = MutableLiveData()
    override val areasState: MutableLiveData<AreasState> = MutableLiveData()
    override val ingredientsState: MutableLiveData<IngredientsState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun fetchAllCategories() {
        val subscription = categoryRepository
            .fetchAllCategories()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it) {
                    is Resource.Loading -> categoriesState.value = CategoriesState.Loading
                    is Resource.Success -> categoriesState.value = CategoriesState.Success(it.data)
                    is Resource.Error -> categoriesState.value =CategoriesState.Error("Error happened while fetching data from the server")
                }
            },{
                categoriesState.value =CategoriesState.Error("Error happened while fetching data from the server")
            })
        subscriptions.add(subscription)
    }

    override fun fetchAllAreas() {
        val subscription = categoryRepository
            .fetchAllAreas()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it) {
                    is Resource.Loading -> areasState.value = AreasState.Loading
                    is Resource.Success -> areasState.value = AreasState.Success(it.data)
                    is Resource.Error -> areasState.value =AreasState.Error("Error happened while fetching data from the server")
                }
            },{
                areasState.value =AreasState.Error("Error happened while fetching data from the server")
            })
        subscriptions.add(subscription)
    }

    override fun fetchAllIngredients() {
        val subscription = categoryRepository
            .fetchAllIngredients()
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it) {
                    is Resource.Loading -> ingredientsState.value = IngredientsState.Loading
                    is Resource.Success -> ingredientsState.value = IngredientsState.Success(it.data)
                    is Resource.Error -> ingredientsState.value = IngredientsState.Error("Error happened while fetching data from the server")
                }
            },{
                ingredientsState.value = IngredientsState.Error("Error happened while fetching data from the server")
            })
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}