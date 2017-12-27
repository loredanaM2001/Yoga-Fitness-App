package com.hugeardor.vidit.yogafitnessapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hugeardor.vidit.yogafitnessapp.Interface.ItemClickListener;
import com.hugeardor.vidit.yogafitnessapp.Model.Exercise;
import com.hugeardor.vidit.yogafitnessapp.R;
import com.hugeardor.vidit.yogafitnessapp.ViewExercise;

import java.util.List;

/**
 * Created by vidit on 26/12/17.
 */

class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    public ImageView image ;
    public TextView text ;

    private ItemClickListener itemClickListener ;


    public RecyclerViewHolder(View itemView) {
        super(itemView);

        image = (ImageView)itemView.findViewById(R.id.ex_image);
        text = (TextView) itemView.findViewById(R.id.ex_name);

    itemView.setOnClickListener(this);

    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition());

    }
}

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {


    private Context context;
    private List<Exercise> exerciseList ;

    public RecyclerViewAdapter(List<Exercise> exerciseList, Context context) {
        this.context =  context;
        this.exerciseList = exerciseList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext()) ;
        View itemView = inflater.inflate(R.layout.item_exercise, parent, false) ;

        return new RecyclerViewHolder(itemView) ;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

            holder.image.setImageResource(exerciseList.get(position).getImage_id());
        holder.text.setText(exerciseList.get(position).getName());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position) {

                //Call to the new Activity
               // Toast.makeText(context,"Click to"+exerciseList.get(position).getName(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context , ViewExercise.class);
                intent.putExtra("image_id", exerciseList.get(position).getImage_id());
                intent.putExtra("name", exerciseList.get(position).getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK) ;
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
