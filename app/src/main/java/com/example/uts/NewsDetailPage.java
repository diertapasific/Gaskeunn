package com.example.uts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsDetailPage extends AppCompatActivity {
    ImageView newsImage;
    TextView newsTitle, newsDate, newsDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail_page);

        newsImage = findViewById(R.id.newsImage);
        newsTitle = findViewById(R.id.newsTitle);
        newsDate = findViewById(R.id.newsDate);
        newsDesc = findViewById(R.id.newsDesc);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            newsTitle.setText(extras.getString("newsTitle"));
            newsDate.setText(extras.getString("newsDate"));
            if (extras.getString("newsTitle").equals("Richard won again!")){
                newsDesc.setText(R.string.desc1);
                newsImage.setImageResource(R.drawable.news1);
            } else if (extras.getString("newsTitle").equals("Richard won first place!")){
                newsDesc.setText(R.string.desc2);
                newsImage.setImageResource(R.drawable.news2);
            } else if (extras.getString("newsTitle").equals("Highest jump ever?")){
                newsDesc.setText(R.string.desc3);
                newsImage.setImageResource(R.drawable.news3);
            } else if (extras.getString("newsTitle").equals("Fun in the city!")){
                newsDesc.setText(R.string.desc4);
                newsImage.setImageResource(R.drawable.news4);
            } else if (extras.getString("newsTitle").equals("Two winner! How?")){
                newsDesc.setText(R.string.desc5);
                newsImage.setImageResource(R.drawable.news5);
            }
        }
    }
}