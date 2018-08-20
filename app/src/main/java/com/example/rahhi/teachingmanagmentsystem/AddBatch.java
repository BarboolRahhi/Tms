package com.example.rahhi.teachingmanagmentsystem;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Process;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.util.Calendar;

public class AddBatch extends AppCompatActivity {


    DatePickerDialog datePickerDialog;

    EditText batchName,batchTime,batchPlace,startDate;
    Button createBatch;
    Firebase firebase;

    ProgressDialog mprogress;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_batch);

        imageView = findViewById(R.id.mback);

        batchName = (EditText) findViewById(R.id.batchName);
        batchTime = (EditText) findViewById(R.id.batchTime);
        batchPlace = (EditText) findViewById(R.id.batchPlace);
        createBatch = (Button) findViewById(R.id.createBatch);
        startDate = (EditText) findViewById(R.id.mstartDate);

        mprogress = new ProgressDialog(this);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(AddBatch.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                startDate.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }



        });



        Firebase.setAndroidContext(getApplicationContext());


        createBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String mBatch = batchName.getText().toString();
                String mTime = batchTime.getText().toString();
                String mPlace = batchPlace.getText().toString();
                String mDate = startDate.getText().toString();

                if(!TextUtils.isEmpty(mBatch) || !TextUtils.isEmpty(mTime) || !TextUtils.isEmpty(mPlace) || !TextUtils.isEmpty(mDate)){
                    createBatchs();

                }



            }
        });


    }

    private void createBatchs() {


        firebase = new Firebase("https://teachingmanagementsystem-4327a.firebaseio.com/Batches/"+batchName.getText().toString());
        Firebase batchname = firebase.child("batchName");
        batchname.setValue(batchName.getText().toString());
        Firebase timeFirebase = firebase.child("timeTable");
        timeFirebase.setValue(batchTime.getText().toString());
        Firebase placeFirebase = firebase.child("placeName");
        placeFirebase.setValue(batchPlace.getText().toString());
        Firebase dateFirebase = firebase.child("startDate");
        dateFirebase.setValue(startDate.getText().toString());


    }


}
