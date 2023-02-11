package com.example.mathpuzzle;

import static com.example.mathpuzzle.config.count;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.GridLayoutManager;


public class SelectPuzzle_Activity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView imageView;
    String str;
    PuzzleActivity_Adapter puzzleActivity_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_puzzle);
        recyclerView=findViewById(R.id.recyclerView);
        puzzleActivity_adapter=new PuzzleActivity_Adapter(SelectPuzzle_Activity.this);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(puzzleActivity_adapter);
        imageView=findViewById(R.id.next);
        count++;
        if (count==1) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(SelectPuzzle_Activity.this, SelectPuzzle_Activity.class);
                    intent.putExtra("lable", "page1");
                    startActivity(intent);
                    finish();
                }
            });
            str = getIntent().getStringExtra("lable");
            if (getIntent().getExtras() != null) {
                recyclerView.setAdapter(puzzleActivity_adapter);
            }
        }
        else
        {
            //imageView.setEnabled(false);
            imageView.setVisibility(View.INVISIBLE);
        }


    }
}