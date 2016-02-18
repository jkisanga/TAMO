package data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.tanapaapp.tzchoice.tamo.AppController;
import com.tanapaapp.tzchoice.tamo.ParkDetailActivity;
import com.tanapaapp.tzchoice.tamo.R;

/**
 * Created by tzchoice on 1/21/2016.
 */
public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ParkReviewViewHolder> {



    public static float[] rating;
    public static String[] comments;
    private Activity context;

    public ReviewAdapter(Activity context, float[] rating, String[] comments)
    {
        this.context = context;
        this.rating = rating;
        this.comments = comments;
    }

    public static class ParkReviewViewHolder extends RecyclerView.ViewHolder {


        TextView tvReview;

        public ParkReviewViewHolder(View itemView) {
            super(itemView);
            tvReview = (TextView) itemView.findViewById(R.id.tvReview);

        }
    }

    @Override
    public ParkReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_row,parent, false);
        ParkReviewViewHolder pvh = new ParkReviewViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ParkReviewViewHolder holder, final int position) {
       holder.tvReview.setText(comments[position].toString());


    }

    @Override
    public int getItemCount() {
        return rating.length;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
