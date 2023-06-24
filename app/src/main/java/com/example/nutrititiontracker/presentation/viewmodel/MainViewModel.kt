package com.example.nutrititiontracker.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrititiontracker.data.models.CategoriesResponse
import com.example.nutrititiontracker.data.models.Resource
import com.example.nutrititiontracker.data.models.UserEntity
import com.example.nutrititiontracker.data.repository.MealRepository
import com.example.nutrititiontracker.data.repository.UserRepository
import com.example.nutrititiontracker.presentation.contract.MainContract
import com.example.nutrititiontracker.presentation.view.states.CategoriesState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val userRepository: UserRepository,
    private val mealRepository: MealRepository
): ViewModel(), MainContract.ViewModel {

    override val loggedUser: MutableLiveData<UserEntity> = MutableLiveData()
    override val categories: MutableLiveData<List<CategoriesResponse>> = MutableLiveData()
    override val categoriesState: MutableLiveData<CategoriesState> = MutableLiveData()

    private val subscriptions = CompositeDisposable()

    override fun fetchAllCategories() {
        val subscription = mealRepository
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
    }
    override fun insertUser(userEntity: UserEntity) {
        val subscription = userRepository
            .insertUser(userEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                println("USPESNO DODATO U BAZU")
            },{
                println("ERROR")
            })
        subscriptions.add(subscription)
    }

    override fun getUser(username: String, password: String) {
        val subscription = userRepository
            .getUser(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
//                println("$it logged")
                loggedUser.value = it
            },{
                println(it)
            })
        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}