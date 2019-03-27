package com.pleasedo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pleasedo.planner.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class userDataAdapter extends RecyclerView.Adapter<userDataAdapter.userViewHolder> {


    private ArrayList<String> eTitle = new ArrayList<>();
    private ArrayList<String> eDescription = new ArrayList<>();
    private ArrayList<String> eStartDate = new ArrayList<>();
    private ArrayList<String> eEndDate = new ArrayList<>();
    private ArrayList<String> eTime = new ArrayList<>();
    private Context context;

    public userDataAdapter(Context context, ArrayList<String> eTitle, ArrayList<String> eDescription, ArrayList<String> eStartDate, ArrayList<String> eEndDate, ArrayList<String> eTime) {
        this.eTitle = eTitle;
        this.eDescription = eDescription;
        this.eStartDate = eStartDate;
        this.eEndDate = eEndDate;
        this.eTime = eTime;
        this.context = context;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_card, parent, false);
        userViewHolder holder = new userViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull userViewHolder userViewHolder, int i) {
        userViewHolder.title.setText(eTitle.get(i));
        userViewHolder.time.setText(eTime.get(i));
        userViewHolder.description.setText(eDescription.get(i));
        userViewHolder.startDate.setText(eStartDate.get(i));
        userViewHolder.endDate.setText(eEndDate.get(i));



    }


    @Override
    public int getItemCount() {
        return eTitle.size();
    }

    public class userViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView)
        ImageView imageView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.description)
        TextView description;
        @BindView(R.id.startDate)
        TextView startDate;
        @BindView(R.id.endDate)
        TextView endDate;
        @BindView(R.id.time)
        TextView time;
        @BindView(R.id.card_layout)
        RelativeLayout cardLayout;

        public userViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
