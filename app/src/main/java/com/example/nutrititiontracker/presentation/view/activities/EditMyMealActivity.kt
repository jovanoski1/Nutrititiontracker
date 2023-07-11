package com.example.nutrititiontracker.presentation.view.activities

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.nutrititiontracker.data.models.MealEntity
import com.example.nutrititiontracker.data.models.MealType
import com.example.nutrititiontracker.databinding.ActivityEditMyMealBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.util.*


class EditMyMealActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditMyMealBinding
    private lateinit var mealToEdit:MealEntity
    private var output: String? = null
    private val mealsViewModel: MealsContract.ViewModel by viewModel<MealsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditMyMealBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras
        if (extras!=null){
            mealToEdit = extras.getSerializable("mealToEdit") as MealEntity
            initUi()
        }

        initListeners()
    }

    private fun initListeners() {
        binding.cancelBtn.setOnClickListener {
            finish()
        }

        binding.saveMealBtn.setOnClickListener {
            val day = binding.datePicker.dayOfMonth
            val month = binding.datePicker.month
            val year = binding.datePicker.year
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            val mealType:MealType = binding.mealSpinner.selectedItem as MealType
            mealToEdit.image = if (output==null)  mealToEdit.image else output!!

            mealToEdit.mealType = mealType
            mealToEdit.plannedDate = calendar.time
            mealToEdit.name = binding.mealNameTv.text.toString()

            mealsViewModel.updateMeal(mealToEdit)
            println(mealToEdit)
            Toast.makeText(this, "Successfully updated meal", Toast.LENGTH_LONG).show()

            val intent = Intent()
            finish()
        }

        binding.mealImageIv.setOnClickListener {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this@EditMyMealActivity, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(
                    this@EditMyMealActivity,
                    arrayOf(Manifest.permission.CAMERA),
                    1
                )
            }
            else {
                ActivityCompat.requestPermissions(this@EditMyMealActivity,
                    arrayOf(Manifest.permission.CAMERA), 1)
            }
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(this@EditMyMealActivity,
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
        binding.mealSpinner.setSelection(mealTypeValues.indexOf(mealToEdit.mealType))
        val c = Calendar.getInstance()
        c.time = mealToEdit.plannedDate
        val year = c[Calendar.YEAR]
        val month = c[Calendar.MONTH]
        val day = c[Calendar.DAY_OF_MONTH]
        binding.datePicker.updateDate(year, month, day)

        if (mealToEdit.image!!.contains("https")) {
            Picasso.get().load(mealToEdit.image).into(binding.mealImageIv)
        }
        else {
            val decodedByteArray = android.util.Base64.decode(mealToEdit.image, android.util.Base64.DEFAULT)
            val decodedBitmap = BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
            binding.mealImageIv.setImageBitmap(decodedBitmap)
        }

        binding.mealNameTv.setText(mealToEdit.name)
    }
}