package com.example.nutrititiontracker.presentation.view.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.nutrititiontracker.R
import com.example.nutrititiontracker.databinding.StatsDialogBinding
import com.example.nutrititiontracker.presentation.contract.MealsContract
import com.example.nutrititiontracker.presentation.viewmodel.MealsViewModel
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*
import kotlin.math.roundToInt


class StatsDialog(
) : DialogFragment() {

    private var _binding: StatsDialogBinding? = null
    private val binding get() = _binding!!
    private val mealsViewModel: MealsContract.ViewModel by sharedViewModel<MealsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StatsDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding.chart1){
            axisRight.isEnabled = false
            animateX(1200, Easing.EaseInSine)

            description.isEnabled = false

            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.valueFormatter = MyAxisFormatter()
            xAxis.granularity = 1F
            xAxis.textSize = 20F
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(false)
            axisLeft.setDrawGridLines(false)
            extraRightOffset = 30f

            legend.isEnabled = false
            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.form = Legend.LegendForm.LINE
        }

        initObserver()
    }

    private fun initObserver() {
        mealsViewModel.mealsForUser.observe(this, Observer {
            val dates = ArrayList<Entry>()
            val arr = ArrayList<Int>(List(7) { 0 })
            for(meal in it){
                val calendar = Calendar.getInstance()
                calendar.time = meal.plannedDate

                println(meal.name + " " + meal.plannedDate)
                when (calendar.get(Calendar.DAY_OF_WEEK)) {
                    Calendar.MONDAY -> {arr[0]++}
                    Calendar.TUESDAY -> {arr[1]++}
                    Calendar.WEDNESDAY -> {arr[2]++}
                    Calendar.THURSDAY -> {arr[3]++}
                    Calendar.FRIDAY -> {arr[4]++}
                    Calendar.SATURDAY -> {arr[5]++}
                    else -> arr[6]++
                }
            }
            for (i in 0..6){
                dates.add(Entry(i.toFloat(), arr[i].toFloat()))
            }

            val weekOneSales = LineDataSet(dates, "Meals per day")
            weekOneSales.lineWidth = 3f
            weekOneSales.valueTextSize = 15f
            weekOneSales.mode = LineDataSet.Mode.CUBIC_BEZIER
            weekOneSales.color = ContextCompat.getColor(context!!, R.color.red)
            weekOneSales.valueTextColor = ContextCompat.getColor(context!!, R.color.black)
//            weekOneSales.enableDashedLine(20F, 10F, 0F)

            val dataSet = ArrayList<ILineDataSet>()
            dataSet.add(weekOneSales)

            val lineData = LineData(dataSet)
            binding.chart1.data = lineData

            binding.chart1.invalidate()
        })
    }

    inner class MyAxisFormatter : IndexAxisValueFormatter() {

        private var items = arrayListOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")

        override fun getAxisLabel(value: Float, axis: AxisBase?): String? {
            val index = value.toInt()
            return if (index < items.size) {
                items[index]
            } else {
                null
            }
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

    private fun setDataToLineChart() {

        val weekOneSales = LineDataSet(week1(), "Week 1")
        weekOneSales.lineWidth = 3f
        weekOneSales.valueTextSize = 15f
        weekOneSales.mode = LineDataSet.Mode.CUBIC_BEZIER
        weekOneSales.color = ContextCompat.getColor(context!!, R.color.red)
        weekOneSales.valueTextColor = ContextCompat.getColor(context!!, R.color.red)
        weekOneSales.enableDashedLine(20F, 10F, 0F)

//        val weekTwoSales = LineDataSet(week2(), "Week 2")
//        weekTwoSales.lineWidth = 3f
//        weekTwoSales.valueTextSize = 15f
//        weekTwoSales.mode = LineDataSet.Mode.CUBIC_BEZIER
//        weekTwoSales.color = ContextCompat.getColor(context!!, R.color.black)
//        weekTwoSales.valueTextColor = ContextCompat.getColor(context!!, R.color.black)
//        weekTwoSales.enableDashedLine(20F, 10F, 0F)


        val dataSet = ArrayList<ILineDataSet>()
        dataSet.add(weekOneSales)
//        dataSet.add(weekTwoSales)

        val lineData = LineData(dataSet)
        binding.chart1.data = lineData

        binding.chart1.invalidate()
    }

    private fun week1(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 15f))
        sales.add(Entry(1f, 16f))
        sales.add(Entry(2f, 13f))
        sales.add(Entry(3f, 22f))
        sales.add(Entry(4f, 20f))
        return sales
    }

    private fun week2(): ArrayList<Entry> {
        val sales = ArrayList<Entry>()
        sales.add(Entry(0f, 11f))
        sales.add(Entry(1f, 13f))
        sales.add(Entry(2f, 18f))
        sales.add(Entry(3f, 16f))
        sales.add(Entry(4f, 22f))
        return sales
    }
}