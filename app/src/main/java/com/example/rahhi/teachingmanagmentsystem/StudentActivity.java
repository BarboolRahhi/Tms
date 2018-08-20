package com.example.rahhi.teachingmanagmentsystem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentActivity extends AppCompatActivity {

    private FloatingActionButton mAdd;

    private Toolbar mToolBar;

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        final String batchNam  = getIntent().getStringExtra("batch");

        mRecyclerView = findViewById(R.id.recycleView1);
        mRecyclerView.setHasFixedSize(true);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mReff = mFirebaseDatabase.getReference("Batches").child(batchNam).child("student");



        mAdd = (FloatingActionButton) findViewById(R.id.floatingActionButton1);

        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentActivity.this, AddStudent.class);
                i.putExtra("id", batchNam);
                startActivity(i);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Student, ViewHolder1> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Student, ViewHolder1>(
                        Student.class,
                        R.layout.row1,
                        ViewHolder1.class,
                        mReff
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder1 viewHolder, Student model, int position) {

                        viewHolder.setDetails(getApplicationContext(), model.getName(), model.getImageUrl());


                    }

                };
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
