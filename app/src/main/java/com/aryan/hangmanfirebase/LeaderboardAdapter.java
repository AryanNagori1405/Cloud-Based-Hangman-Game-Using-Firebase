package com.aryan.hangmanfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    Context context;
    ArrayList<Player> list;

    public LeaderboardAdapter(Context context, ArrayList<Player> list){
        this.context=context;
        this.list=list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,score;

        public ViewHolder(View v){
            super(v);
            name=v.findViewById(R.id.playerName);
            score=v.findViewById(R.id.playerScore);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v= LayoutInflater.from(context).inflate(R.layout.item_player,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Player p=list.get(position);
        holder.name.setText((position+1)+". "+p.getName());
        holder.score.setText("Score: "+p.getScore());
    }

    @Override
    public int getItemCount(){
        return list.size();
    }
}

