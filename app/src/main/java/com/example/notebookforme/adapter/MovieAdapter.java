package com.example.notebookforme.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.notebookforme.MainActivity;
import com.example.notebookforme.R;
import com.example.notebookforme.ToDoItem;
import com.example.notebookforme.model.Movie;
import com.example.notebookforme.task.DownloadImageTask;
import com.loopj.android.image.SmartImageView;

import java.util.ArrayList;

/**
 * Created by Kreliou on 20/04/2016.
 */
public class MovieAdapter extends RecyclerView
        .Adapter<MovieAdapter
        .MovieHolder> {

    private ArrayList<Movie> mDataset;
    private static MyClickListener myClickListener;

    public static class MovieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleTextView;
        TextView ratingTextView;
        TextView releaseTextView;
        RatingBar ratingBar;
        TextView overviewTextView;
        SmartImageView posterImageView;

        public MovieHolder(View itemView){
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            ratingTextView = (TextView) itemView.findViewById(R.id.ratingTextView);
            releaseTextView = (TextView) itemView.findViewById(R.id.releasetextView);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);
            overviewTextView = (TextView) itemView.findViewById(R.id.overviewTextView);
            posterImageView = (SmartImageView) itemView.findViewById(R.id.posterImageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           // myClickListener.onItemClick(getPosition(), v); //MIGHT BUG
        }
    }

    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }

    public MovieAdapter(ArrayList<Movie> myDataset) {
        if (myDataset == null)
            mDataset = new ArrayList<>();
        else
            mDataset = myDataset;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list_movie, parent, false);

        MovieHolder dataObjectHolder = new MovieHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.titleTextView.setText(mDataset.get(position).getTitle());
        holder.ratingTextView.setText(mDataset.get(position).getVote().toString());
        holder.releaseTextView.setText(mDataset.get(position).getRelease());
        holder.ratingBar.setRating((float) mDataset.get(position).getVote().doubleValue());
        holder.overviewTextView.setText(mDataset.get(position).getOverview());

        if (mDataset.get(position).getPoster() != "")
            holder.posterImageView.setImageUrl(mDataset.get(position).getPoster());
        else
            holder.posterImageView.setImageUrl("http://ingridwu.dmmdmcfatter.com/wp-content/uploads/2015/01/placeholder.png");
    }

    public void addItem(Movie dataObj, int index) {
        mDataset.add(dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    public void clear()
    {
        mDataset.clear();
        notifyDataSetChanged();
    }

    public void resetDataSet(ArrayList<Movie> myDataset){
        mDataset.clear();
        mDataset = myDataset;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }



/*
    Context mContext;
    int mLayoutResourceId;

    public MovieAdapter(Context context, int layoutResourceId) {
        super(context, layoutResourceId);

        mContext = context;
        mLayoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        final Movie currentItem = getItem(position);

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(mLayoutResourceId, parent, false);
        }
        row.setTag(currentItem);

        TextView titleTextView = (TextView) row.findViewById(R.id.titleTextView);
        titleTextView.setText(currentItem.getTitle());

        TextView ratingTextView = (TextView) row.findViewById(R.id.ratingTextView);
        ratingTextView.setText(currentItem.getVote().toString());

        TextView releaseTextView = (TextView) row.findViewById(R.id.releasetextView);
        releaseTextView.setText(currentItem.getRelease());

        RatingBar ratingBar = (RatingBar) row.findViewById(R.id.ratingBar);
        ratingBar.setRating((float) currentItem.getVote().doubleValue());

        TextView overviewTextView = (TextView) row.findViewById(R.id.overviewTextView);
        overviewTextView.setText(currentItem.getOverview());

        SmartImageView posterImageView = (SmartImageView) row.findViewById(R.id.posterImageView);
        if (currentItem.getPoster() != "")
            posterImageView.setImageUrl(currentItem.getPoster());
        else
            posterImageView.setImageUrl("http://ingridwu.dmmdmcfatter.com/wp-content/uploads/2015/01/placeholder.png");

        return row;
    }*/
}
