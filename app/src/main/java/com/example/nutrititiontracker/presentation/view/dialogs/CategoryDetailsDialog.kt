package com.example.nutrititiontracker.presentation.view.dialogs

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.nutrititiontracker.data.models.CategoryResponse
import com.example.nutrititiontracker.databinding.CategoryDetailDialogBinding
import com.squareup.picasso.Picasso

class CategoryDetailsDialog(
    private val category: CategoryResponse
) : DialogFragment() {

    private var _binding: CategoryDetailDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CategoryDetailDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.categoryDetailsNameTv.text = category.strCategory
        binding.categoryDetailsDescriptionTv.text = category.strCategoryDescription
        binding.categoryDetailsDescriptionTv.movementMethod = ScrollingMovementMethod.getInstance()
        Picasso.get().load(category.strCategoryThumb).into(binding.categoryDetailsImageIv)

        binding.categoryDetailsCloseBtn.setOnClickListener {
            parentFragmentManager.beginTransaction().remove(this).commit()
        }
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