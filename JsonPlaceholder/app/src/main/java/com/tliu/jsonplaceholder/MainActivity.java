package com.tliu.jsonplaceholder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        size = intent.getIntExtra("size", 0);
        Log.d("size in Main",""+size);

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new ImageAdapter(this, size));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "Item:  " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    /** Called when the user clicks the Send button */
    public void toAnim(View view) {
        Intent intent = new Intent(this, AnimationActivity.class);
        intent.putExtra("size", size);
        startActivity(intent);
    }
}
