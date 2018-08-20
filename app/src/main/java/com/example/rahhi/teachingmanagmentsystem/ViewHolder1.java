package com.example.rahhi.teachingmanagmentsystem;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder1 extends RecyclerView.ViewHolder {


    View mView;

    public ViewHolder1(View itemView) {
        super(itemView);

        mView = itemView;
    }

    public void setDetails(Context ctx, String name, String imageUrl) {

        TextView mTextBatch = mView.findViewById(R.id.studentname);
        ImageView mImage = mView.findViewById(R.id.imageV);


        mTextBatch.setText(name);

        Picasso.get().load(imageUrl).into(mImage);

    }
}
