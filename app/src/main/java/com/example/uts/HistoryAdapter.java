package com.example.uts;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder>{
    Context context;
    ArrayList<History> histories;
    public HistoryAdapter(Context context, ArrayList<History> items) {
        this.context = context;
        this.histories = items;
    }

    @NonNull
    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
//        if (TransactionDatabase.getInstance().getLoggedIn().equals(transactions.get(position).getUser())){
        holder.historyName.setText(histories.get(position).getName());
        holder.historyDate.setText(histories.get(position).getDate());
        holder.historyQ.setText(histories.get(position).getQuantity());
//    }
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView historyName, historyDate, historyQ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            historyName = itemView.findViewById(R.id.historyName);
            historyDate = itemView.findViewById(R.id.historyDate);
            historyQ = itemView.findViewById(R.id.historyQ);
        }
    }
}
