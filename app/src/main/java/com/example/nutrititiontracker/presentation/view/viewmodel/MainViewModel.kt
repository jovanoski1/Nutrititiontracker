package com.example.nutrititiontracker.presentation.view.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrititiontracker.data.models.UserEntity
import com.example.nutrititiontracker.data.repository.UserRepository
import com.example.nutrititiontracker.presentation.view.contract.MainContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val userRepository: UserRepository
): ViewModel(), MainContract.ViewModel {

    override val loggedUser: MutableLiveData<UserEntity> = MutableLiveData()
    private val subscriptions = CompositeDisposable()


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