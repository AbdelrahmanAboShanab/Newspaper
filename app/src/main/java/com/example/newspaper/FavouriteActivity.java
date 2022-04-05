package com.example.newspaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.newspaper.adapters.NewsAdapter;
import com.example.newspaper.pojo.News;
import com.example.newspaper.viewModels.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    NewsViewModel newsViewModel;
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    Button toMainButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        recyclerView = findViewById(R.id.favRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        toMainButton = findViewById(R.id.toMainBtn);
        toMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavouriteActivity.this, MainActivity.class));
            }
        });

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        newsViewModel.viewModelGetNews();
        newsViewModel.viewModelGetNewsList().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                newsAdapter = new NewsAdapter(news,getBaseContext());
                recyclerView.setAdapter(newsAdapter);
            }
        });
    }

    public void setupSwipe() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder
                    , @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swiped = viewHolder.getAdapterPosition();
                News n = newsAdapter.getAt(swiped);
                newsViewModel.viewModelDelete(n.getTitle());
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}