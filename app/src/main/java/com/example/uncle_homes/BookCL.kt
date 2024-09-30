package com.example.uncle_homes

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import java.util.*

class BookCL : AppCompatActivity() {

    private val services = arrayOf(
        "House Cleaning", "Plumbing", "Electrical Repairs", "Gardening", "Pool Cleaning",
        "Carpentry", "Painting", "Roof Repairs", "Pest Control", "Security Installation"
    )

    private lateinit var etDateTime: EditText
    private lateinit var tasksAdapter: ArrayAdapter<String>
    private val tasks = mutableListOf<String>()
    private lateinit var selectedService: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        val serviceListView: ListView = findViewById(R.id.serviceListView)
        val serviceTitle: TextView = findViewById(R.id.tvServiceTitle)
        val numberOfRooms: EditText = findViewById(R.id.etNumberOfRooms)
        val recurringSwitch: SwitchCompat = findViewById(R.id.switchRecurring)
        etDateTime = findViewById(R.id.etDateTime)
        val proceedButton: Button = findViewById(R.id.btnProceed)
        val taskEditText: EditText = findViewById(R.id.etTask)
        val addTaskButton: Button = findViewById(R.id.btnAddTask)
        val tasksListView: ListView = findViewById(R.id.lvTasks)

        // Initialize tasks list adapter
        tasksAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, tasks)
        tasksListView.adapter = tasksAdapter

        // Step 1: Service Selection (ListView for choosing service)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, services)
        serviceListView.adapter = adapter

        // Handle service selection from the list
        serviceListView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            selectedService = services[position]
            serviceTitle.text = "Service: $selectedService"


            findViewById<View>(R.id.serviceDetailsSection).visibility = View.VISIBLE
        }


        etDateTime.setOnClickListener {
            showDateTimePicker()
        }


        proceedButton.setOnClickListener {
            // Collect details for the selected service
            val rooms = numberOfRooms.text.toString()
            val dateTime = etDateTime.text.toString()
            val recurring = recurringSwitch.isChecked


            Toast.makeText(this, "Service: $selectedService\nRooms: $rooms\nDateTime: $dateTime\nRecurring: $recurring", Toast.LENGTH_LONG).show()


        }

        // Step 4: Add Task Button to add tasks to the task list
        addTaskButton.setOnClickListener {
            val task = taskEditText.text.toString()
            if (task.isNotEmpty()) {
                tasks.add(task)
                tasksAdapter.notifyDataSetChanged()
                taskEditText.text.clear()
            }
        }
    }

    // Helper function to show Date and Time picker dialog
    private fun showDateTimePicker() {
        val calendar = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, { _, year, month, day ->
            val timePicker = TimePickerDialog(this, { _, hour, minute ->
                etDateTime.setText("$day/${month + 1}/$year $hour:$minute")
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
            timePicker.show()
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))

        datePicker.show()
    }
}
