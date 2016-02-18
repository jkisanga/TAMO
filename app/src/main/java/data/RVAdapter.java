package data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ParkViewHolder> {

    public static float[] rating_caches;
    public static Integer[] rating_counts;
    public static String[] short_desc;
    public static String[] long_desc;
    public static String[] icon;
    public static String[] names;
    public static Integer[] ids;
    private Activity context;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public RVAdapter(Activity context,  float[] rating_caches, Integer[] rating_counts, String[] short_desc,
                     String[] long_desc,String[] icon, String[] names,Integer[] ids )
    {
        this.context = context;
        this.rating_caches = rating_caches;
        this.rating_counts = rating_counts;
        this.short_desc = short_desc;
        this.long_desc = long_desc;
        this.icon = icon;
        this.names = names;
        this.ids = ids;
    }

    public static class ParkViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        RatingBar tvRatingCache;
        TextView tvRatingCount;
        TextView tvName;
        TextView tvShortDesc;
        NetworkImageView imgIcon;

        public ParkViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            tvRatingCache = (RatingBar) itemView.findViewById(R.id.ratingBar);
            tvRatingCount = (TextView) itemView.findViewById(R.id.parkRatingCount);
            tvShortDesc = (TextView) itemView.findViewById(R.id.parkShortDesc);
            imgIcon = (NetworkImageView) itemView.findViewById(R.id.imgIcon);
            tvName = (TextView) itemView.findViewById(R.id.parkName);
        }
    }

    @Override
    public ParkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent, false);
        ParkViewHolder pvh = new ParkViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ParkViewHolder holder, final int position) {
       holder.tvName.setText(names[position]);
      holder.tvRatingCount.setText(rating_counts[position] + " Reviews");
      holder.tvShortDesc.setText(short_desc[position]);
        holder.tvRatingCache.setNumStars(5);
        holder.tvRatingCache.setRating(rating_caches[position]);

        //pass intent to park details class
        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        holder.imgIcon.setImageUrl(icon[position], imageLoader);

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context,ParkDetailActivity.class);
                intent.putExtra(Constants.PARK_NAMES, names[position].toString());
                intent.putExtra(Constants.PARK_LONG_DESC, long_desc[position].toString());
               intent.putExtra(Constants.PARK_IMAGE_ICON, icon[position]);
               intent.putExtra(Constants.PARK_ID, ids[position]);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return names.length;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
