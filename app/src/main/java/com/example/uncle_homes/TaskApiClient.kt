package com.example.uncle_homes

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject

class TaskApiClient(context: Context) {
    private val requestQueue: RequestQueue = Volley.newRequestQueue(context.applicationContext)
    private val gson = Gson()
    private val baseUrl = "http://127.0.0.1"

    fun getAllTasks(onSuccess: (List<Task>) -> Unit, onError: (String) -> Unit) {
        val request = JsonArrayRequest(
            Request.Method.GET,
            baseUrl,
            null,
            { response ->
                val tasks = gson.fromJson(response.toString(), Array<Task>::class.java).toList()
                onSuccess(tasks)
            },
            { error ->
                onError(error.message ?: "An error occurred")
            }
        )
        requestQueue.add(request)
    }

    fun getTask(taskId: Int, onSuccess: (Task) -> Unit, onError: (String) -> Unit) {
        val request = JsonObjectRequest(
            Request.Method.GET,
            "$baseUrl/$taskId",
            null,
            { response ->
                val task = gson.fromJson(response.toString(), Task::class.java)
                onSuccess(task)
            },
            { error ->
                onError(error.message ?: "An error occurred")
            }
        )
        requestQueue.add(request)
    }

    fun createTask(task: Task, onSuccess: (Task) -> Unit, onError: (String) -> Unit) {
        val jsonBody = JSONObject(gson.toJson(task))
        val request = JsonObjectRequest(
            Request.Method.POST,
            baseUrl,
            jsonBody,
            { response ->
                val createdTask = gson.fromJson(response.toString(), Task::class.java)
                onSuccess(createdTask)
            },
            { error ->
                onError(error.message ?: "An error occurred")
            }
        )
        requestQueue.add(request)
    }

    fun updateTask(task: Task, onSuccess: (Task) -> Unit, onError: (String) -> Unit) {
        val jsonBody = JSONObject(gson.toJson(task))
        val request = JsonObjectRequest(
            Request.Method.PUT,
            "$baseUrl/${task.id}",
            jsonBody,
            { response ->
                val updatedTask = gson.fromJson(response.toString(), Task::class.java)
                onSuccess(updatedTask)
            },
            { error ->
                onError(error.message ?: "An error occurred")
            }
        )
        requestQueue.add(request)
    }

    fun deleteTask(taskId: Int, onSuccess: () -> Unit, onError: (String) -> Unit) {
        val request = JsonObjectRequest(
            Request.Method.DELETE,
            "$baseUrl/$taskId",
            null,
            { onSuccess() },
            { error ->
                onError(error.message ?: "An error occurred")
            }
        )
        requestQueue.add(request)
    }
}