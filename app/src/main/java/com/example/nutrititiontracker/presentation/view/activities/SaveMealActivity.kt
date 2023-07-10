package com.example.nutrititiontracker.presentation.view.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.MealResponse
import com.example.nutrititiontracker.data.models.MealType
import com.example.nutrititiontracker.databinding.ActivitySaveMealBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File


class SaveMealActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySaveMealBinding
    private lateinit var mealToSave:MealResponse
    private val mealsViewModel: MealsContract.ViewModel by viewModel<MealsViewModel>()
    private val sharedPreferences: SharedPreferences by inject()
    private var output: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySaveMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras!=null){
            mealToSave = extras.getSerializable("mealToSave") as MealResponse
        }

        val id:Long = sharedPreferences.getLong("userId", -1L)

        initUi()
        initListeners()
    }


    private fun initListeners() {

        binding.mealImageIv.setOnClickListener {
            println("KLIKNUO NA SLIKU")
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@SaveMealActivity, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(
                    this@SaveMealActivity,
                    arrayOf(Manifest.permission.CAMERA),
                    1
                )
            }
            else {
                ActivityCompat.requestPermissions(this@SaveMealActivity,
                    arrayOf(Manifest.permission.CAMERA), 1)
            }
        }

        binding.saveMealBtn.setOnClickListener{
            val mealType:MealType = binding.mealSpinner.selectedItem as MealType
            val id:Long = sharedPreferences.getLong("userId", -1L)

//            val imagePath =
            mealsViewModel.insertMeal(MealEntity(
                category = mealToSave.strCategory,
                image = if (output==null)  mealToSave.strMealThumb else output!! ,
                instructions = mealToSave.strInstructions,
                name = mealToSave.strMeal,
                mealType = mealType,
                strIngredient1 = mealToSave.strIngredient1,
                strIngredient2 = mealToSave.strIngredient2,
                strIngredient3 = mealToSave.strIngredient3,
                strIngredient4 = mealToSave.strIngredient4,
                strIngredient5 = mealToSave.strIngredient5,
                strIngredient6 = mealToSave.strIngredient6,
                strIngredient7 = mealToSave.strIngredient7,
                strIngredient8 = mealToSave.strIngredient8,
                strIngredient9 = mealToSave.strIngredient9,
                strIngredient10 = mealToSave.strIngredient10,
                strIngredient11 = mealToSave.strIngredient11,
                strIngredient12 = mealToSave.strIngredient12,
                strIngredient13 = mealToSave.strIngredient13,
                strIngredient14 = mealToSave.strIngredient14,
                strIngredient15 = mealToSave.strIngredient15,
                strIngredient16 = mealToSave.strIngredient16,
                strIngredient17 = mealToSave.strIngredient17,
                strIngredient18 = mealToSave.strIngredient18,
                strIngredient19 = mealToSave.strIngredient19,
                strIngredient20 = mealToSave.strIngredient20,
                strMeasure1 = mealToSave.strMeasure1,
                strMeasure2 = mealToSave.strMeasure2,
                strMeasure3 = mealToSave.strMeasure3,
                strMeasure4 = mealToSave.strMeasure4,
                strMeasure5 = mealToSave.strMeasure5,
                strMeasure6 = mealToSave.strMeasure6,
                strMeasure7 = mealToSave.strMeasure7,
                strMeasure8 = mealToSave.strMeasure8,
                strMeasure9 = mealToSave.strMeasure9,
                strMeasure10 = mealToSave.strMeasure10,
                strMeasure11 = mealToSave.strMeasure11,
                strMeasure12 = mealToSave.strMeasure12,
                strMeasure13 = mealToSave.strMeasure13,
                strMeasure14 = mealToSave.strMeasure14,
                strMeasure15 = mealToSave.strMeasure15,
                strMeasure16 = mealToSave.strMeasure16,
                strMeasure17 = mealToSave.strMeasure17,
                strMeasure18 = mealToSave.strMeasure18,
                strMeasure19 = mealToSave.strMeasure19,
                strMeasure20 = mealToSave.strMeasure20,
                urlToVideo = mealToSave.strYoutube,
                userId = id
            ))

            Toast.makeText(this, "Successfully saved meal", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@SaveMealActivity,
                            Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                        capturePhoto()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }

    private fun capturePhoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        startActivityForResult(cameraIntent, 200)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == 200 && data != null){
            binding.mealImageIv.setImageBitmap(data.extras?.get("data") as Bitmap)
            val bitmap = data.extras?.get("data") as Bitmap
            output = bitmapToBase64(bitmap)
        }
    }

    private fun bitmapToBase64(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()
        return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
    }
    private fun initUi() {
        val mealTypeValues:Array<MealType> = MealType.values()
        val adapter = ArrayAdapter<MealType>(this, android.R.layout.simple_spinner_item, mealTypeValues)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mealSpinner.adapter = adapter
        Picasso.get().load(mealToSave.strMealThumb).into(binding.mealImageIv)
        binding.mealNameTv.text = mealToSave.strMeal
    }
}