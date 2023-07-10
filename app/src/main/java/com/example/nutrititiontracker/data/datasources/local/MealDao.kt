package com.example.nutrititiontracker.data.datasources.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.nutrititiontracker.data.models.MealEntity
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
abstract class MealDao {

    @Query("SELECT * FROM meals WHERE user_id == :user_id")
    abstract fun getMealsForUsr(user_id:Long): Observable<List<MealEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMeal(mealEntity: MealEntity): Completable

    @Delete
    abstract fun deleteMeal(mealEntity: MealEntity): Completable

    @Update
    abstract fun updateMeal(mealEntity: MealEntity): Completable
}