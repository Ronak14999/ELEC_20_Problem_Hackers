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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    EditText uname,uemail,upass,ucpass,uphone,uaddr;
    Button usignup;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myRef;
    String name,phone,pass,email,cpass,addr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        uname=(EditText)findViewById(R.id.editText4);
        uemail=(EditText)findViewById(R.id.editText7);
        uphone=(EditText)findViewById(R.id.editText8);
        upass=(EditText)findViewById(R.id.editText5);
        ucpass=(EditText)findViewById(R.id.editText6);
        uaddr=(EditText)findViewById(R.id.editText9);
        usignup=(Button)findViewById(R.id.button2);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        mAuth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        myRef=firebaseDatabase.getReference("Users");


        usignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 name=uname.getText().toString().trim();
                  email=uemail.getText().toString().trim();
                 pass=upass.getText().toString().trim();
                 cpass=ucpass.getText().toString().trim();
                 addr=uaddr.getText().toString().trim();
                 phone=uphone.getText().toString().trim();



                if(name.isEmpty())
                {
                    uname.setError("Name is required");
                    return;
                }
                if(email.isEmpty())
                {
                    uemail.setError("Email is required");

                    return;
                }
                if(phone.isEmpty())
                {
                    uphone.setError("Phone number is required");

                    return;
                }
                if(pass.isEmpty())
                {
                    upass.setError("Password id required");
                    return;
                }
                if(!cpass.equals(pass))
                {
                    ucpass.setError("Password must be same");
                    return;
                }
                if(addr.isEmpty())
                {
                    uaddr.setError("Address is required");

                    return;
                }
                progressDialog.show();
                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    user details=new user(
                                           name,
                                            email,
                                            phone,
                                            addr


                                    );
                                    firebaseDatabase.getReference("Users")
                                            .child(mAuth.getCurrentUser().getUid())
                                            .setValue(details).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(Signup.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(Signup.this,LoginPage.class));


                                        }
                                    });



                                } else {
                                    Toast.makeText(Signup.this, "Error", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }

                                // ...
                            }
                        });



            }
        });

    }
}
