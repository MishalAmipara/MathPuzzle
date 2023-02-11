package com.example.mathpuzzle;
import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
public class PuzzleActivity_Adapter extends RecyclerView.Adapter<PuzzleActivity_Adapter.View_Holder>
{
    Activity activity;
    SharedPreferences preferences;
    public PuzzleActivity_Adapter(Activity activity) {
        this.activity=activity;
        preferences=activity.getSharedPreferences("mypref",MODE_PRIVATE);
    }

    @NonNull
    @Override
    public PuzzleActivity_Adapter.View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(activity).inflate(R.layout.activity_select_puzzle_item,parent,false);
        View_Holder view_holder=new View_Holder(view);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(@NonNull PuzzleActivity_Adapter.View_Holder holder, int position)
    {

        String status=preferences.getString("levelstatus"+position,"pending");
        int lastlevel=preferences.getInt("lastlevel",0);
        System.out.println("lastlevel="+lastlevel);
        System.out.println("level = "+position + "== "+ status);
        if(status.equals("win"))
        {
            holder.imageView.setImageResource(R.drawable.tick);
            holder.textView.setText(""+(position+1));
            holder.textView.setVisibility(View.VISIBLE);
        }
        if(status.equals("skip") || position==lastlevel+1)
        {
            holder.imageView.setImageResource(0);
            holder.textView.setText(""+(position+1));
            holder.textView.setVisibility(View.VISIBLE);
        }

       if(status.equals("win") || status.equals("skip") || position==lastlevel+1)
       {
           holder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Intent intent=new Intent(activity,Puzzles_Activity.class);
                   intent.putExtra("level",holder.getAdapterPosition());
                   activity.startActivity(intent);

//                activity.finish();
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        return 24;
    }

    public class View_Holder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView textView;
        public View_Holder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.item_img);
            textView=itemView.findViewById(R.id.text);
        }
    }
}
