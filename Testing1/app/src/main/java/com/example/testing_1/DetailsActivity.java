package com.example.testing_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailsActivity extends AppCompatActivity {

    TextView house,area,city,state,contact,priority;
    ImageView roomimage;
    String Key="";
    String ImageUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        house=(TextView) findViewById(R.id.tvhouseno);
        area=(TextView) findViewById(R.id.tvarea);
        city=(TextView) findViewById(R.id.tvcity);
        state=(TextView) findViewById(R.id.tvstate);
        priority=(TextView) findViewById(R.id.tvpriority);
        roomimage=(ImageView)findViewById(R.id.ivImage);
        contact=(TextView)findViewById(R.id.tvcontact);


        Bundle mBundle=getIntent().getExtras();
        if(mBundle!=null)
        {
           // roomimage.setImageResource(mBundle.getInt("Image"));
            house.setText(mBundle.getString("House-No"));
            area.setText(mBundle.getString("Area"));
            city.setText(mBundle.getString("City"));
            state.setText(mBundle.getString("State"));
            priority.setText(mBundle.getString("Priority"));
            contact.setText(mBundle.getString("Contact"));
             Key=mBundle.getString("Key");
             ImageUrl=mBundle.getString("Image");
            Glide.with(this).load(mBundle.getString("Image")).into(roomimage);



        }
    }

    public void btnDelete(View view) {

        final DatabaseReference reference=FirebaseDatabase.getInstance().getReference("Room_Details");
        FirebaseStorage storage=FirebaseStorage.getInstance();
        StorageReference storageReference=storage.getReferenceFromUrl(ImageUrl);

        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

                reference.child(Key).removeValue();
                Toast.makeText(DetailsActivity.this, "Room Deleted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}
