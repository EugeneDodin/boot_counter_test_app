package com.dodin.auratechassignment.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.dodin.auratechassignment.R

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.textData.observe(this, ::bindData)
        viewModel.fetchRebootsData()
    }

    private fun bindData(message: String?) {
        message?.let { specialText ->
            findViewById<TextView>(R.id.rebootEvents).text = specialText
        }
    }
}
