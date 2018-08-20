package com.example.rahhi.teachingmanagmentsystem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public ViewHolder(View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void  setDetails(Context ctx, String batchName, String timeTable, String placeName , String startDate){

            TextView mTextBatch = mView.findViewById(R.id.batchName);
            TextView mTextTime = mView.findViewById(R.id.batchTime);
            TextView mTextPlace = mView.findViewById(R.id.batchPlace);
            TextView mTextDate = mView .findViewById(R.id.startDate);

            mTextBatch.setText(batchName);
            mTextTime.setText(timeTable);
            mTextPlace.setText(placeName);
            mTextDate.setText(startDate);

        }

    }

