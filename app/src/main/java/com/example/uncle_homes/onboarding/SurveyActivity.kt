package com.example.uncle_homes.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.uncle_homes.R
import com.example.uncle_homes.users.ServiceProviderInformation

class SurveyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_survey_sp)

        val nextQuestion: Button = findViewById(R.id.nextQuestion)

        nextQuestion.setOnClickListener {
            val intent = Intent(this, ServiceProviderInformation::class.java)
            startActivity(intent)
        }
    }
}