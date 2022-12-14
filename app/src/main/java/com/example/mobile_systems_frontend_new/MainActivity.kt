package com.example.mobile_systems_frontend_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_systems_frontend_new.repository.Repository
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.ViewFlipper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.mobile_systems_frontend_new.database.DataRoomDatabase
import com.example.mobile_systems_frontend_new.model.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel:MainViewModel
    lateinit var RV: RecyclerView
    lateinit var List: ArrayList<Signal>
    lateinit var Adapter: RecyclerAdapter
    private var layoutManager: RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RV = findViewById(R.id.recyclerView)
        List = ArrayList()

        layoutManager = LinearLayoutManager(this)
        RV.layoutManager = layoutManager
        Adapter = RecyclerAdapter(items = List)
        RV.adapter = Adapter

        getUserData()

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer{ response ->
            val mapText: TextView = findViewById(R.id.mapTextView)
            var map = response.map
            Log.d("Response", map)
            map = map.replace("0", "□")
            map = map.replace("1", "■")
            mapText.text = map
            mapText.movementMethod = ScrollingMovementMethod()
        })

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val viewFlipper: ViewFlipper = findViewById(R.id.vf)
                viewFlipper.displayedChild = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })

        viewModel.getSignals()
        viewModel.signals.observe(this, Observer{ response ->
            Log.d("si", response.toString())

            List.clear()
            List.addAll(response.signals)
            Log.d("s", List.size.toString())
            Adapter.notifyDataSetChanged()
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
            insertUserData(macAddress, strengthOne.toInt(), strengthTwo.toInt(), strengthThree.toInt())
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

    private fun getUserData() {
        var mac = ""
        var strengthOne = ""
        var strengthTwo = ""
        var strengthThree = ""
        val thread = Thread {
            // this waits for the user data from the database and sets the text fields
            val db =
                Room.databaseBuilder(
                    applicationContext,
                    DataRoomDatabase::class.java,
                    "mobile-app-database-0.1"
                )
                    .build()
            val dao = db.dao()
            var userData = dao.getInputData()
            if (userData.isEmpty()) {
                val newData = InputData(1, "", 0, 0, 0)
                dao.insertInputData(newData)
                userData = dao.getInputData()
            }
            db.close()
            mac = userData[0].user.toString()
            strengthOne = userData[0].str1.toString()
            strengthTwo = userData[0].str2.toString()
            strengthThree = userData[0].str3.toString()
        }
        thread.start()
        // wait for thread to finish
        thread.join()

        val macText: TextView = findViewById(R.id.macAdressText)
        val strengthOneText: TextView = findViewById(R.id.strengthOneText)
        val strengthTwoText: TextView = findViewById(R.id.strengthTwoText)
        val strengthThreeText: TextView = findViewById(R.id.strengthThreeText)
        macText.text = mac
        strengthOneText.text = strengthOne
        strengthTwoText.text = strengthTwo
        strengthThreeText.text = strengthThree
    }

    private fun insertUserData(mac: String, str1: Int, str2: Int, str3: Int) {
        val thread = Thread {
            // this waits for the user data from the database and sets the text fields
            val db =
                Room.databaseBuilder(
                    applicationContext,
                    DataRoomDatabase::class.java,
                    "mobile-app-database-0.1"
                ).build()
            val userDataDao = db.dao()
            var userData = userDataDao.getInputData()
            val newData = InputData(1, mac, str1, str2, str3)
            if(userData.isEmpty()){
                userDataDao.insertInputData(newData)
            } else {
                userDataDao.updateInputData(newData)
            }
            db.close()
        }
        thread.start()
        // wait for thread to finish
        thread.join()
    }
}