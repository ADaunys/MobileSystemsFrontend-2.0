package com.example.mobile_systems_frontend_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_systems_frontend_new.model.PostUserData
import com.example.mobile_systems_frontend_new.model.Strengths
import com.example.mobile_systems_frontend_new.model.Users
import com.example.mobile_systems_frontend_new.repository.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val button = findViewById<TextView>(R.id.button)
        button.setOnClickListener {
            val macAddress = findViewById<TextView>(R.id.macAdressText).text.toString()
            val strengthOne = findViewById<TextView>(R.id.strengthOneText).text.toString().toInt()
            val strengthTwo = findViewById<TextView>(R.id.strengthTwoText).text.toString().toInt()
            val strengthThree = findViewById<TextView>(R.id.strengthThreeText).text.toString().toInt()
            val postUserData = PostUserData(users = arrayOf(
                Users(macAddress, arrayOf(
                    Strengths("wiliboxas1", strengthOne),
                    Strengths("wiliboxas2", strengthTwo),
                    Strengths("wiliboxas3", strengthThree)
                    )
                )))
            viewModel.calculateLocation(postUserData)
            viewModel.calculationResponse.observe(this, Observer{ response ->
                Log.d("Response", response.toString())
                var helloTextView: TextView = findViewById(R.id.text_id)
                var finalMessage = ""
                for(item in response.responses) {
                    finalMessage += item + "\n"
                }
                helloTextView.setText(finalMessage)
            })
        }
        /*
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer{ response ->
            Log.d("Response", response.toString())
            var helloTextView: TextView = findViewById(R.id.text_id)
            helloTextView.setText(response.toString())
        })*/
    }
}