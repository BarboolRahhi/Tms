package com.example.rahhi.teachingmanagmentsystem;


import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class BatchFragment extends Fragment {


    public BatchFragment()
    {
        // Required empty public constructor
    }
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        final View  view = inflater.inflate(R.layout.fragment_batch, container, false);


        //  Button addBatch = (Button) view.findViewById(R.id.addBatch);
        FloatingActionButton addBatch = (FloatingActionButton) view.findViewById(R.id.floatingActionButton);

        mRecyclerView = view.findViewById(R.id.recycleViw);
        mRecyclerView.setHasFixedSize(true);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference("Batches");


        addBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getContext(),AddBatch.class));
            }
        });






        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Model, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.row,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {

                        viewHolder.setDetails(getContext(), model.getBatchName(), model.getTimeTable(), model.getPlaceName(),model.getStartDate());

                        final String batch = getItem(position).getBatchName();
                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getContext(), StudentActivity.class);
                                intent.putExtra("batch",batch);
                                startActivity(intent);
                            }
                        });

                    }
                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}
