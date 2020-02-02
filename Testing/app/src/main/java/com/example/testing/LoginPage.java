package com.example.testing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    EditText uemail,upass;
     TextView textView;
     ProgressDialog progressDialog;
     Button login;
     private FirebaseAuth mAuth;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        textView=(TextView)findViewById(R.id.textView3);
        uemail=(EditText)findViewById(R.id.editText);
        upass=(EditText)findViewById(R.id.editText2);

        mAuth=FirebaseAuth.getInstance();
        login=(Button)findViewById(R.id.button);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginPage.this,Signup.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=uemail.getText().toString();
                String pass=upass.getText().toString();

                if(email.isEmpty())
                {
                    uemail.setError("Email is required");

                    return;
                }

                if(pass.isEmpty())
                {
                    upass.setError("Password id required");
                    return;
                }

                progressDialog.show();

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(LoginPage.this, "SuccessFully LogIn", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(LoginPage.this,AfterLogin.class));
                                    progressDialog.dismiss();

                                } else {

                                    Toast.makeText(LoginPage.this, "Invalid Email or Password", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }

                                // ...
                            }
                        });
            }
        });


    }


}
