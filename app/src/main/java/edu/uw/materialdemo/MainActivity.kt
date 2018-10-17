package edu.uw.materialdemo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ListView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private val TAG = "Main"

    private val data: MutableList<String> = mutableListOf<String>() //data to be manipulated
    private lateinit var mAdapter: ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set the XML toolbar as the ActionBar
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //data
        data.addAll(resources.getStringArray(R.array.robot_array))

        //ListView setup
        mAdapter = ArrayAdapter<String>(this, R.layout.list_item, R.id.txt_item, data)
        val listView = findViewById<ListView>(R.id.list_view)
        listView.setAdapter(mAdapter)


        val button = findViewById<ImageButton>(R.id.btn_more)
        button.setOnClickListener {
            Toast.makeText(this, "You clicked me!", Toast.LENGTH_SHORT);

        }
    }
}
