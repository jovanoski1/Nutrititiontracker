package com.example.nutrititiontracker.presentation.view.recycler.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.data.models.AreaResponse

class AreaSpinnerAdapter(
    context: Context,
    resource: Int,
    private val areaList: List<AreaResponse>,
    private val attributeName: String
) : ArrayAdapter<AreaResponse>(context, resource, areaList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createView(position, convertView, parent)
    }

    private fun createView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.spinner_item, parent, false)

        val area = areaList[position]
        val attributeValue = when (attributeName) {
            "strArea" -> area.strArea
            // Add additional attribute cases as needed
            else -> ""
        }

        val textView = view.findViewById<TextView>(R.id.spinnerTv)
        textView.text = attributeValue

        return view
    }
}