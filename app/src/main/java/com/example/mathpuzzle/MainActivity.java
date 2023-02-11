package com.example.mathpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity
{
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences=getSharedPreferences("mypref",MODE_PRIVATE);
        int lastlevel=preferences.getInt("lastlevel",0);
        System.out.println(lastlevel);

        findViewById(R.id.continew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Puzzles_Activity.class);
                intent.putExtra("level",lastlevel+1);
                startActivity(intent);
            }
        });

        findViewById(R.id.puzzles).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SelectPuzzle_Activity.class);
                startActivity(intent);
            }
        });
    }
}