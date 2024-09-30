package com.example.uncle_homes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import android.graphics.Color
import android.graphics.Typeface

class AnalyticsCL : AppCompatActivity() {

    // Variable for PieChart
    private lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_analytics_cl)

        // Initialize pieChart with its ID
        pieChart = findViewById(R.id.pieChart)

        // Set up pie chart configuration
        pieChart.apply {
            setUsePercentValues(true)
            description.isEnabled = false
            setExtraOffsets(5f, 10f, 5f, 5f)
            dragDecelerationFrictionCoef = 0.95f
            setDrawHoleEnabled(true)
            setHoleColor(Color.WHITE)
            setTransparentCircleColor(Color.WHITE)
            setTransparentCircleAlpha(110)
            holeRadius = 58f
            transparentCircleRadius = 61f
            setDrawCenterText(true)
            rotationAngle = 0f
            isRotationEnabled = true
            isHighlightPerTapEnabled = true
            animateY(1400, Easing.EaseInOutQuad)
            legend.isEnabled = false
            setEntryLabelColor(Color.WHITE)
            setEntryLabelTextSize(12f)
        }

        // Data entries for the pie chart
        val entries = ArrayList<PieEntry>().apply {
            add(PieEntry(70f, "Outdoor"))
            add(PieEntry(20f, "Handyman"))
            add(PieEntry(10f, "Indoor"))

        }

        // Set up the data set
        val dataSet = PieDataSet(entries, "Mobile OS").apply {
            setDrawIcons(false)
            sliceSpace = 3f
            iconsOffset = MPPointF(0f, 40f)
            selectionShift = 5f
            colors = arrayListOf(
                resources.getColor(R.color.glossy_green, theme),
                resources.getColor(R.color.glossy_yellow, theme),
                resources.getColor(R.color.glossy_red, theme)
            )
        }

        // Set up pie chart data
        val data = PieData(dataSet).apply {
            setValueFormatter(PercentFormatter(pieChart))
            setValueTextSize(15f)
            setValueTypeface(Typeface.DEFAULT_BOLD)
            setValueTextColor(Color.WHITE)
        }

        // Set data to the pie chart and refresh
        pieChart.data = data
        pieChart.highlightValues(null)
        pieChart.invalidate()
    }
}

