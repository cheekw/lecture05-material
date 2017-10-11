package edu.uw.materialdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main";

    private ArrayList<String> mData;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //set the XML toolbar as the ActionBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ImageButton button = (ImageButton)findViewById(R.id.btn_more);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked me!", Toast.LENGTH_SHORT).show();
            }
        });

        //list view
        mData = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.robot_array)));

        mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txt_item, mData);

        ListView list = (ListView)findViewById(R.id.list_view);
        list.setAdapter(mAdapter);

    }
}
