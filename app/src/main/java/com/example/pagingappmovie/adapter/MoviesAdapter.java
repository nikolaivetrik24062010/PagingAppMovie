package com.example.pagingappmovie.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.pagingappmovie.databinding.SingleMovieItemBinding;
import com.example.pagingappmovie.model.Movie;
import com.example.pagingappmovie.viewmodel.MovieViewModel;

import kotlinx.coroutines.CoroutineDispatcher;

public class MoviesAdapter extends PagingDataAdapter<Movie,
        MoviesAdapter.MovieVIewHolder> {

    public static final int LOADING_ITEM = 0;
    public static final int MOVIE_ITEM = 1;

    RequestManager glide;

    public MoviesAdapter(@NonNull DiffUtil.ItemCallback<Movie> diffCallback, RequestManager glide) {
        super(diffCallback);
        this.glide = glide;
    }

    @NonNull
    @Override
    public MovieVIewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieVIewHolder(SingleMovieItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public int getItemViewType(int position) {
        return position == getItemCount() ? MOVIE_ITEM : LOADING_ITEM;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieVIewHolder holder, int position) {
        Movie currentMovie = getItem(position);
        if (currentMovie != null) {
            glide.load("https://image.tmdb.org/t/p/w500" + currentMovie.getPosterPath())
                    .into(holder.movieItemBinding.imageViewMovie);

            holder.movieItemBinding.textViewRating.setText(String.valueOf(currentMovie.getVoteAverage()));
        }

    }

    public class MovieVIewHolder extends RecyclerView.ViewHolder {
        SingleMovieItemBinding movieItemBinding;

        public MovieVIewHolder(@NonNull SingleMovieItemBinding movieItemBinding) {
            super(movieItemBinding.getRoot());

            this.movieItemBinding = movieItemBinding;
        }
    }
}
