package com.example.uncle_homes

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class WalletCL: AppCompatActivity() {

    // Variable for the bar chart
    lateinit var barChart: BarChart

    // Variables for bar data sets
    lateinit var barDataSet1: BarDataSet
    lateinit var barDataSet2: BarDataSet

    // Creating a list for storing entries
    lateinit var barEntries: ArrayList<BarEntry>

    // Array for displaying months with years
    val months = arrayOf("Jan 2023", "Feb 2023", "Mar 2023", "Apr 2023", "May 2023", "Jun 2023")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet_cl)

        // Initializing the variable for the bar chart
        barChart = findViewById(R.id.barChartBalance)

        // Creating new bar data sets
        barDataSet1 = BarDataSet(getBarEntriesOne(), "Product A Earnings")
        barDataSet1.color = resources.getColor(R.color.glossy_yellow, theme)

        barDataSet2 = BarDataSet(getBarEntriesTwo(), "Product B Earnings")
        barDataSet2.color = Color.BLUE

        // Adding bar data sets to the bar data
        val data = BarData(barDataSet1, barDataSet2)

        // Setting the data to the bar chart
        barChart.data = data

        // Removing the description label of the bar chart
        barChart.description.isEnabled = false

        // Getting the x-axis of the bar chart
        val xAxis = barChart.xAxis

        // Setting value formatter to the x-axis and adding the months to the x-axis labels
        xAxis.valueFormatter = IndexAxisValueFormatter(months)

        // Setting center axis labels for the bar chart
        xAxis.setCenterAxisLabels(true)

        // Setting the position of the x-axis to the bottom
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // Setting granularity for the x-axis labels
        xAxis.granularity = 1f

        // Enabling granularity for the x-axis
        xAxis.isGranularityEnabled = true

        // Making the bar chart draggable
        barChart.isDragEnabled = true

        // Setting the visible range for the bar chart
        barChart.setVisibleXRangeMaximum(3f)

        // Adding bar space and group spacing to the chart
        val barSpace = 0.1f
        val groupSpace = 0.5f

        // Setting the width of the bars
        data.barWidth = 0.15f

        // Setting the minimum axis value for the chart
        barChart.xAxis.axisMinimum = 0f

        // Animating the chart
        barChart.animateY(1000)

        // Grouping bars and adding spacing to them
        barChart.groupBars(0f, groupSpace, barSpace)

        // Invalidating the bar chart
        barChart.invalidate()

        val btnAddCard: Button = findViewById(R.id.btnAddCard)


        btnAddCard.setOnClickListener {
            val intent = Intent(this, AddCardActivity::class.java)
            startActivity(intent)
        }
    }

    // ArrayList for the first set of bar entries (Product A)
    private fun getBarEntriesOne(): ArrayList<BarEntry> {
        barEntries = ArrayList()
        barEntries.add(BarEntry(1f, 4000f))
        barEntries.add(BarEntry(2f, 6000f))
        barEntries.add(BarEntry(3f, 8000f))
        barEntries.add(BarEntry(4f, 2000f))
        barEntries.add(BarEntry(5f, 4000f))
        barEntries.add(BarEntry(6f, 1000f))
        return barEntries
    }

    // ArrayList for the second set of bar entries (Product B)
    private fun getBarEntriesTwo(): ArrayList<BarEntry> {
        barEntries = ArrayList()
        barEntries.add(BarEntry(1f, 8000f))
        barEntries.add(BarEntry(2f, 12000f))
        barEntries.add(BarEntry(3f, 4000f))
        barEntries.add(BarEntry(4f, 1000f))
        barEntries.add(BarEntry(5f, 7000f))
        barEntries.add(BarEntry(6f, 3000f))
        return barEntries
    }
}

