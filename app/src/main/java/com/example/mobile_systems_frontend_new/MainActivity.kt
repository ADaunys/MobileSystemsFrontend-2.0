package com.example.mobile_systems_frontend_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_systems_frontend_new.repository.Repository
import android.text.method.ScrollingMovementMethod

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.calculateLocation()
        viewModel.calculationResponse.observe(this, Observer{ response ->
            var helloTextView: TextView = findViewById(R.id.text_id)
            var finalMessage = ""
            for(item in response.responses) {
                finalMessage += item + "\n"
            }
            helloTextView.text = finalMessage
        })
    }

    fun gotoHome(view: View){
        setContentView(R.layout.activity_main)
    }

    fun gotoMap(view: View){
        setContentView(R.layout.map_view)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer{ response ->
            val mapText: TextView = findViewById(R.id.mapTextView)
            var map = response.map
            map = map.replace("1", "■")
            map = map.replace("0", "□")
            mapText.text = map
            mapText.movementMethod = ScrollingMovementMethod()
        })
    }

    fun gotoSignals(view: View){
        setContentView(R.layout.signal_strength_view)
    }

    fun onClick(view: View) {
        setContentView(R.layout.map_view)


    }
}