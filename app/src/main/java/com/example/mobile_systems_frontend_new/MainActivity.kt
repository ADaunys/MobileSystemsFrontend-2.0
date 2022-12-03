package com.example.mobile_systems_frontend_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_systems_frontend_new.model.PostUserData
import com.example.mobile_systems_frontend_new.model.Strengths
import com.example.mobile_systems_frontend_new.model.Users
import com.example.mobile_systems_frontend_new.repository.Repository
import android.text.method.ScrollingMovementMethod
import android.widget.ViewFlipper
import com.example.mobile_systems_frontend_new.viewModels.UserMapViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val viewFlipper: ViewFlipper = findViewById(R.id.vf)
                viewFlipper.displayedChild = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })


    }

    fun getCoords(view: View){
        val macAddress = findViewById<TextView>(R.id.macAdressText).text.toString()
        val strengthOne = findViewById<TextView>(R.id.strengthOneText).text.toString()
        val strengthTwo = findViewById<TextView>(R.id.strengthTwoText).text.toString()
        val strengthThree = findViewById<TextView>(R.id.strengthThreeText).text.toString()
        try {
            val postUserData = PostUserData(users = arrayOf(
                Users(macAddress, arrayOf(
                    Strengths("wiliboxas1", strengthOne.toInt()),
                    Strengths("wiliboxas2", strengthTwo.toInt()),
                    Strengths("wiliboxas3", strengthThree.toInt())
                ))))

            viewModel.calculateLocation(postUserData)
            viewModel.calculationResponse.observe(this, Observer{ response ->
                var helloTextView: TextView = findViewById(R.id.text_id)
                var finalMessage = ""
                for(item in response.responses) {
                    finalMessage += item + "\n"
                }
                helloTextView.text = finalMessage
            })
        } catch(e: Exception) {
            val postUserData = PostUserData()
            viewModel.calculateLocation(postUserData)
            viewModel.calculationResponse.observe(this, Observer{ response ->
                var helloTextView: TextView = findViewById(R.id.text_id)
                var finalMessage = ""
                for(item in response.responses) {
                    finalMessage += item + "\n"
                }
                helloTextView.text = finalMessage
            })
        }
    }

    fun gotoMap(view: View){
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


    //CIA PRASIDEDA ROOM
//    private val userMapViewModel: UserMapViewModel by viewModels {
//        WordViewModelFactory((application as WordsApplication).repository)
//    }
}