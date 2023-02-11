package com.example.mathpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Puzzles_Activity extends AppCompatActivity implements View.OnClickListener
{

    String[] ans={"10","20","30","40","50","60","70","10","20","30","40","50","60","70"};
    Button[] button=new Button[10];
    Button submit;
    TextView txtAns;
    String temp,t;
    ImageView imageView,img,skip;
    TextView levelno;
    int level=0;
    int[] imgarr={
            R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7,
            R.drawable.p8,
            R.drawable.p9,
            R.drawable.p10,
            R.drawable.p11,
            R.drawable.p12,
            R.drawable.p13,
            R.drawable.p14,
            R.drawable.p15,

    };

    //get
    SharedPreferences preferences;
    //add update delete
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzles);
        txtAns=findViewById(R.id.txtAns);
        img=findViewById(R.id.img);
        levelno=findViewById(R.id.levelno);
        submit=findViewById(R.id.submit);
        skip=findViewById(R.id.skip);
        submit.setOnClickListener(this);

        preferences=getSharedPreferences("mypref",MODE_PRIVATE);
        editor=preferences.edit();


        if(getIntent().getExtras()!=null)
        {
            level=getIntent().getIntExtra("level",0);
        }
        for(int i=0;i<10;i++)
        {
            int id=getResources().getIdentifier("btn"+i,"id",getPackageName());
            button[i]=findViewById(id);

        }
        img.setImageResource(imgarr[level]);
        levelno.setText("Level "+(level+1));
        for (int i=0;i<button.length;i++)
        {
            button[i].setOnClickListener(this);
        }
        imageView=findViewById(R.id.erase);
        imageView.setOnClickListener(this);
        skip.setOnClickListener(view -> {
            editor.putInt("lastlevel",level);
            editor.putString("levelstatus"+level,"skip");
            editor.commit();
            level++;
            Intent  intent=new Intent(this,Puzzles_Activity.class);
            intent.putExtra("level",level);
            startActivity(intent);
            finish();
        });
    }

    @Override
    public void onClick(View view) {
        for (int i=0;i<button.length;i++)
        {
            if(view.getId()==button[i].getId())
            {
                temp=txtAns.getText().toString(); //1
                t=temp+String.valueOf(i);//t=11
                txtAns.setText(""+t);
            }
        }
        if (view.getId()==imageView.getId())
        {
            temp=txtAns.getText().toString().substring(0,txtAns.length()-1);
            txtAns.setText(""+temp);

        }
        if (view.getId()==submit.getId())
        {

            if(txtAns.getText().toString().equals(ans[level]))
            {

                editor.putInt("lastlevel",level);
                editor.putString("levelstatus"+level,"win");
                editor.commit();

                level++;
                Intent  intent=new Intent(this,Puzzles_Activity.class);
                intent.putExtra("level",level);
                startActivity(intent);
                finish();
            }
            else
            {
                System.out.println("hello");
            }


        }

    }
}