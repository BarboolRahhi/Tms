package com.example.rahhi.teachingmanagmentsystem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    private TextInputLayout emailId,password,confirmPassword;
    private Button signupButton,verifiedButton;
    private TextView verificationText;

    private ProgressDialog mProgress;

    TextView mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mText = findViewById(R.id.text2);

        firebaseAuth = FirebaseAuth.getInstance();

        emailId = (TextInputLayout) findViewById(R.id.mEmail1);
        password = (TextInputLayout) findViewById(R.id.mPassword1);
        confirmPassword = (TextInputLayout) findViewById(R.id.mConPassword);
        signupButton = (Button) findViewById(R.id.signinButton);
        verificationText = (TextView) findViewById(R.id.verificatio);
        verifiedButton = (Button) findViewById(R.id.verifybtn);

        mProgress = new ProgressDialog(this);


        final String[] status ={"Start where you are. Use what you have.Do what you can. - Arthur Ashe",
                "The secret of succes is to do the common things uncommonly well- John Maxwell",
                "Success id the sum of small efforts repeated day and day out - Robert Collier",
                " The only place where success comes before work in the dictionary - Vidal Sassoon "};

        final Handler handler = new Handler();
        Runnable runnable = new Runnable() {
            int i= 0;

            @Override
            public void run() {
                 mText.setText(status[i]);
                 i++;
                 if (i > status.length - 1){
                     i = 0;
                 }
                handler.postDelayed(this, 3000);
            }
        };

        handler.postDelayed(runnable,3000);



        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = emailId.getEditText().getText().toString();
                String pd =  password.getEditText().getText().toString();
                String cpd  = confirmPassword.getEditText().getText().toString();

                if(id.isEmpty()|| !Patterns.EMAIL_ADDRESS.matcher(id).matches())
                {
                    emailId.setError("Enter a valid Email Id!");
                    emailId.requestFocus();
                    return;
                }
                if(pd.isEmpty()||pd.length()<6)
                {
                    password.setError("Password Error! Must contain atleast 6 characters.");
                    password.requestFocus();
                    return;
                }
                if(!cpd.equals(pd))
                {
                    confirmPassword.setError("Password do not match!");
                    confirmPassword.requestFocus();
                    return;
                }
                mProgress.setTitle("Sign Up");
                mProgress.setMessage("Please Wait...");
                mProgress.setCanceledOnTouchOutside(false);
                mProgress.show();

                register_user( id,pd);


            }
        });


    }

    private void register_user(String id, String pd) {



        firebaseAuth.createUserWithEmailAndPassword(id,pd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {

                    mProgress.dismiss();
                    Intent openIntent = new Intent(getApplicationContext(), Main2Activity.class);
                    openIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    getApplicationContext().startActivity(openIntent);
                    finish();



                }
                else
                {
                    mProgress.hide();
                    Toast.makeText(SignUpActivity.this, "cannot sign up", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
