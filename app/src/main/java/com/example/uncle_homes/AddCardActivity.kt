package com.example.uncle_homes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.fevziomurtekin.payview.Payview
import com.fevziomurtekin.payview.data.PayModel


class AddCardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)

        // Creating a variable for our pay view and initializing it.
        val payView: Payview = findViewById(R.id.payview)

        payView.setOnDataChangedListener(object : Payview.OnChangelistener{
            override fun onChangelistener(payModel: PayModel?, isFillAllComponent: Boolean) {
                Log.d("PayView", "data : ${payModel?.cardOwnerName} \n " +
                        "is Fill all form component : $isFillAllComponent")

            }

        })

        payView.setPayOnclickListener(View.OnClickListener {
            Log.d("PayView "," clicked. iss Fill all form Component : ${payView.isFillAllComponents}")

        })

    }
}