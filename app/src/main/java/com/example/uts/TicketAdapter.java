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

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>{
    Context context;
    ArrayList<Ticket> tickets;
    public TicketAdapter(Context context, ArrayList<Ticket> items) {
        this.context = context;
        this.tickets = items;
    }

    @NonNull
    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.ticket_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.ViewHolder holder, int position) {
        holder.ticketName.setText(tickets.get(position).getName());
        holder.ticketPrice.setText(tickets.get(position).getPrice());
        holder.ticketImage.setImageResource(tickets.get(position).getImage());

        holder.ticketCv.setOnClickListener(view -> {
            Intent intent = new Intent(context, TicketDetailPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("ticketName",tickets.get(position).getName());
            intent.putExtra("ticketPrice",tickets.get(position).getPrice());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tickets.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView ticketName, ticketPrice;
        ImageView ticketImage;
        CardView ticketCv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ticketImage = itemView.findViewById(R.id.ticketImage);
            ticketName = itemView.findViewById(R.id.ticketName);
            ticketPrice = itemView.findViewById(R.id.ticketPrice);
            ticketCv = itemView.findViewById(R.id.ticketCv);
        }
    }
}
