package com.octa.todo_notes;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList todo_id, todo_title, todo_desc, todo_day;

    CustomAdapter(Activity activity, Context context, ArrayList todo_id, ArrayList todo_title, ArrayList todo_description, ArrayList todo_day){
        this.activity = activity;
        this.context = context;
        this.todo_id = todo_id;
        this.todo_title = todo_title;
        this.todo_desc = todo_description;
        this.todo_day = todo_day;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_todo, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.book_id_txt.setText(String.valueOf(todo_id.get(position)));
        holder.book_title_txt.setText(String.valueOf(todo_title.get(position)));
        holder.book_author_txt.setText(String.valueOf(todo_desc.get(position)));
        holder.book_pages_txt.setText(String.valueOf(todo_day.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, UpdateActivity.class);
//                intent.putExtra("id", String.valueOf(book_id.get(position)));
//                intent.putExtra("title", String.valueOf(book_title.get(position)));
//                intent.putExtra("author", String.valueOf(book_author.get(position)));
//                intent.putExtra("pages", String.valueOf(book_pages.get(position)));
//                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return todo_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.todo_id);
            book_title_txt = itemView.findViewById(R.id.todo_title);
            book_author_txt = itemView.findViewById(R.id.todo_desc);
            book_pages_txt = itemView.findViewById(R.id.todo_date);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }


}
