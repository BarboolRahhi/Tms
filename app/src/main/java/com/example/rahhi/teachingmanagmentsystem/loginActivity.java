package com.example.rahhi.teachingmanagmentsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity {

    ImageView imageView;
    TextInputLayout emailId,password;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
     Button loginButton;

    private ProgressDialog mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageView = findViewById(R.id.backBtn);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        emailId =  findViewById(R.id.mEmail);
        password =  findViewById(R.id.mPassword);
        loginButton =  findViewById(R.id.loginButton);

        mProgressBar = new ProgressDialog(this);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = emailId.getEditText().getText().toString();
                String pd = password.getEditText().getText().toString();

                if(!TextUtils.isEmpty(id) || !TextUtils.isEmpty(pd)){

                    mProgressBar.setTitle("Logging In");
                    mProgressBar.setMessage("Please Wait...");
                    mProgressBar.setCanceledOnTouchOutside(false);
                    mProgressBar.show();

                    loginUser(id, pd);

                }

                }
        });

        }

    private void loginUser(String id, String pd) {

        firebaseAuth.signInWithEmailAndPassword(id,pd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {


                if(task.isSuccessful())
                {
                    mProgressBar.dismiss();
                    Intent openIntent = new Intent(getApplicationContext(), Main2Activity.class);
                    openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getApplicationContext().startActivity(openIntent);
                    finish();

                    //startActivity(new Intent(getApplicationContext(),Main2Activity.class));
                }
                else
                {

                    mProgressBar.hide();
                    Toast.makeText(loginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
