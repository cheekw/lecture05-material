package edu.uw.materialdemo

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {

    private val TAG = "Main"

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RecyclerView.Adapter<*>
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    private val data: MutableList<String> = mutableListOf<String>() //data to be manipulated

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set the XML toolbar as the ActionBar
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        //ImageButton button = (ImageButton)findViewById(R.id.btn_more);
        val button = findViewById<View>(R.id.btn_more) as FloatingActionButton
        button.setOnClickListener {
            Toast.makeText(this, "You clicked me!", Toast.LENGTH_SHORT);

            //adding items to RecyclerView
            val randomIdx = (1..3).shuffled().first()
            data.add(randomIdx,"New item at $randomIdx");
            mAdapter.notifyItemInserted(randomIdx);

//            button.hide();
            val snack = Snackbar.make(it, "You can click me too!", Snackbar.LENGTH_SHORT);
            snack.setAction("Hide forever") {
                button.hide();
            };
            snack.show();

            //doesn't work on emulator now...
//            val options = ActivityOptions.makeSceneTransitionAnimation(this, button as View, "same_fab")
//            startActivity(Intent(this, ScrollingActivity::class.java), options.toBundle())
        }

        data.addAll(resources.getStringArray(R.array.robot_array))

        //setup recyclerview
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = MyAdapter(data)
        mRecyclerView = findViewById<RecyclerView>(R.id.list_view).apply {
            this.layoutManager = mLayoutManager
            this.adapter = mAdapter
        }
    }

    class MyAdapter(private val mDataset: MutableList<String>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder.
        // Each data item is just a string in this case that is shown in a TextView.
        class MyViewHolder(var mTextView: TextView) : RecyclerView.ViewHolder(mTextView)

        // Create new views (invoked by the layout manager)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.MyViewHolder {
            // create a new view
            val textView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.list_item, parent, false) as TextView
            // set the view's size, margins, paddings and layout parameters
            return MyViewHolder(textView)
        }

        // Replace the contents of a view (invoked by the layout manager)
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            holder.mTextView.text = mDataset[position]

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount(): Int = mDataset.size
    }
}
