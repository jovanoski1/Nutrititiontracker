package com.example.nutrititiontracker.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.nutrititiontracker.data.models.AreaResponse
import com.example.nutrititiontracker.data.models.CategoryResponse

class CategorySpinnerAdapter(
    context: Context,
    resource: Int,
    private val categoryList: List<CategoryResponse>,
    private val attributeName: String
) : ArrayAdapter<CategoryResponse>(context, resource, categoryList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(android.R.layout.simple_spinner_item, parent, false)

        val category = categoryList[position]
        val attributeValue = when (attributeName) {
            "idCategory" -> category.idCategory
            "strCategory" -> category.strCategory
            // Add additional attribute cases as needed
            else -> ""
        }

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = attributeValue.toString()

        return view
    }
}