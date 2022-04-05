package com.example.newspaper.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newspaper.R;
import com.example.newspaper.pojo.News;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<News> adapterList;
    Context context;

    public NewsAdapter(List<News> adapterList, Context context) {
        this.adapterList = adapterList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsViewHolder(LayoutInflater.from(parent.getContext()).
                inflate(R.layout.news_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.title.setText(adapterList.get(position).getTitle());
        holder.description.setText(adapterList.get(position).getDescription());
        holder.date.setText(adapterList.get(position).getPublishedAt());
        holder.athor.setText(adapterList.get(position).getAuthor());

        Glide.with(context).load(adapterList.get(position).getUrlToImage())
                .apply(new RequestOptions().override(400, 400))
                .into(holder.imageView);
    }

    public News getAt(int position)
    {
        return adapterList.get(position);
    }
    @Override
    public int getItemCount() {
        return adapterList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, description, date, athor;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.newImg);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            athor = itemView.findViewById(R.id.author);
        }
    }
}
