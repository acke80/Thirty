package se.umu.christofferakrin.thirty.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import se.umu.christofferakrin.thirty.R;


public class ResultRecyclerAdapter extends RecyclerView.Adapter<ResultRecyclerAdapter.ViewHolder>{

    Context context;

    String rounds[];
    String scores[];
    String options[];

    public ResultRecyclerAdapter(Context context, String rounds[], String scores[], String options[]){
        this.context = context;
        this.rounds = rounds;
        this.scores = scores;
        this.options = options;
    }



    @NonNull
    @Override
    public ResultRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResultRecyclerAdapter.ViewHolder holder, int position){
        holder.t1.setText(rounds[position]);
        holder.t2.setText(scores[position]);
        holder.t3.setText(options[position]);
    }

    @Override
    public int getItemCount(){
        return rounds.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView t1, t2, t3;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            t1 = itemView.findViewById(R.id.textView1);
            t2 = itemView.findViewById(R.id.textView2);
            t3 = itemView.findViewById(R.id.textView3);
        }
    }
}
