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

import org.w3c.dom.Text;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    ArrayList<News> news;
    public NewsAdapter(Context context, ArrayList<News> items) {
        this.context = context;
        this.news = items;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.news_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.newsTitle.setText(news.get(position).getTitle());
        holder.newsDate.setText(news.get(position).getDate());
        holder.newsImage.setImageResource(news.get(position).getImage());

        holder.newsCv.setOnClickListener(view -> {
            Intent intent = new Intent(context, NewsDetailPage.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("newsTitle",news.get(position).getTitle());
            intent.putExtra("newsDate",news.get(position).getDate());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
    TextView newsTitle, newsDate;
    ImageView newsImage;
    CardView newsCv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.newsImage);
            newsTitle = itemView.findViewById(R.id.newsTitle);
            newsDate = itemView.findViewById(R.id.newsDate);
            newsCv = itemView.findViewById(R.id.newsCv);
        }
    }
}
