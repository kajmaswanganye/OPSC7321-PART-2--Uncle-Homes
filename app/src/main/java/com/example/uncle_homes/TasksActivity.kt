package com.example.uncle_homes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class TasksActivity : AppCompatActivity() {
    private lateinit var taskRecyclerView: RecyclerView
    private lateinit var addTaskButton: View
    private lateinit var newTaskTitle: TextView
    private lateinit var newTaskDescription: TextView
    private lateinit var taskApiClient: TaskApiClient
    private lateinit var taskAdapter: TaskAdapter
    private val tasks = mutableListOf<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_sp)

        taskRecyclerView = findViewById(R.id.taskRecyclerView)
        addTaskButton = findViewById(R.id.addTaskButton)
        newTaskTitle = findViewById(R.id.newTaskTitle)
        newTaskDescription = findViewById(R.id.newTaskDescription)

        taskApiClient = TaskApiClient(this)
        setupRecyclerView()
        setupAddTaskButton()
        fetchTasks()
    }

    private fun setupRecyclerView() {
        taskAdapter = TaskAdapter(tasks) { task ->
            // Handle task item click (e.g., to edit or delete)
            // For now, we'll just toggle the completion status
            updateTask(task.copy(isCompleted = !task.isCompleted))
        }
        taskRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TasksActivity)
            adapter = taskAdapter
        }
    }

    private fun setupAddTaskButton() {
        addTaskButton.setOnClickListener {
            val title = newTaskTitle.text.toString()
            val description = newTaskDescription.text.toString()
            if (title.isNotBlank()) {
                val newTask = Task(id = 0, title = title, description = description, isCompleted = false)
                createTask(newTask)
            } else {
                Toast.makeText(this, "Please enter a task title", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchTasks() {
        taskApiClient.getAllTasks(
            onSuccess = { fetchedTasks ->
                tasks.clear()
                tasks.addAll(fetchedTasks)
                taskAdapter.notifyDataSetChanged()
            },
            onError = { errorMessage ->
                Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun createTask(task: Task) {
        taskApiClient.createTask(
            task,
            onSuccess = { createdTask ->
                tasks.add(createdTask)
                taskAdapter.notifyItemInserted(tasks.size - 1)
                newTaskTitle.text = ""
                newTaskDescription.text = ""
            },
            onError = { errorMessage ->
                Toast.makeText(this, "Error creating task: $errorMessage", Toast.LENGTH_LONG).show()
            }
        )
    }

    private fun updateTask(task: Task) {
        taskApiClient.updateTask(
            task,
            onSuccess = { updatedTask ->
                val index = tasks.indexOfFirst { it.id == updatedTask.id }
                if (index != -1) {
                    tasks[index] = updatedTask
                    taskAdapter.notifyItemChanged(index)
                }
            },
            onError = { errorMessage ->
                Toast.makeText(this, "Error updating task: $errorMessage", Toast.LENGTH_LONG).show()
            }
        )
    }
}

class TaskAdapter(
    private val tasks: List<Task>,
    private val onItemClick: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task)
    }

    override fun getItemCount() = tasks.size

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.taskTitle)
        private val descriptionTextView: TextView = itemView.findViewById(R.id.taskDescription)
        private val completedCheckBox: CheckBox = itemView.findViewById(R.id.taskCompletedCheckbox)

        fun bind(task: Task) {
            titleTextView.text = task.title
            descriptionTextView.text = task.description
            completedCheckBox.isChecked = task.isCompleted

            itemView.setOnClickListener { onItemClick(task) }
            completedCheckBox.setOnCheckedChangeListener { _, isChecked ->
                if (task.isCompleted != isChecked) {
                    onItemClick(task.copy(isCompleted = isChecked))
                }
            }
        }
    }
}