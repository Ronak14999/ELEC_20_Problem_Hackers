package com.example.testing_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Upload_Details extends AppCompatActivity {

    Spinner sstate, scity, spriority;
    String State, City, Priority;
    ImageView iv_room;
    Uri uri;
    EditText ethouse,etarea,etcontact;
    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload__details);

        sstate = (Spinner) findViewById(R.id.selectState);
        scity = (Spinner) findViewById(R.id.selectCity);
        spriority = (Spinner) findViewById(R.id.selectPriority);
        iv_room = (ImageView) findViewById(R.id.iv_roomImage);
        ethouse=(EditText)findViewById(R.id.house_no) ;
        etarea=(EditText) findViewById(R.id.area);
        etcontact=(EditText) findViewById(R.id.contact);



        List<String> state = new ArrayList<String>();
        state.add("Select State");
        state.add("Bihar");
        state.add("Haryana");
        state.add("Himachal Pradesh");
        state.add("Panjab");
        state.add("Rajasthan");
        state.add("Uttar Pradesh");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, state);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sstate.setAdapter(arrayAdapter);
        sstate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sstate.setSelection(position);
                State = sstate.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<String> city = new ArrayList<String>();
        city.add("Select City");
        city.add("Ajmer");
        city.add("Alwar");
        city.add("Bikaner");
        city.add("Bharatpur");
        city.add("Hamirpur");
        city.add("Jaipur");
        city.add("Jodhpur");
        city.add("Kota");
        city.add("Sikar");
        city.add("Udaipur");


        ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);
        arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scity.setAdapter(arrayAdapter1);
        scity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scity.setSelection(position);
                City = scity.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });



        List<String> priority = new ArrayList<String>();
        priority.add("Select Priority");
        priority.add("Only for Girls");
        priority.add("Only for Boys");
        priority.add("For Both");

        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, priority);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spriority.setAdapter(arrayAdapter2);
        spriority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()

        {
            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
                spriority.setSelection(position);
                Priority = spriority.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){

            }
        });
    }

    public void btnSelectImage(View view) {
        Intent photoPicker=new Intent(Intent.ACTION_PICK);
        photoPicker.setType("image/*");
        startActivityForResult(photoPicker,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==RESULT_OK)
        {
            uri=data.getData();
            iv_room.setImageURI(uri);

        }
        else
        {
            Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
        }
    }

    public void uploadImage(){

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Details Uploading");
        progressDialog.show();


        StorageReference storageReference= FirebaseStorage.getInstance()
                .getReference().child("Room_Details").child(uri.getLastPathSegment());
        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while(!uriTask.isComplete());
                Uri urlImage=uriTask.getResult();
                imageUrl=urlImage.toString();
                Toast.makeText(Upload_Details.this, "Image Uploaded", Toast.LENGTH_SHORT).show();
                upload_Details();
                progressDialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Details.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }


    public void uploaddetailsbtn(View view) {
        uploadImage();
    }

    public void upload_Details(){

        RoomData roomData=new RoomData(
                City,
                etarea.getText().toString(),
                Priority,
                etcontact.getText().toString(),
                imageUrl,
                State,
                ethouse.getText().toString()


        );

        String myCurrentDateTime=DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());

        FirebaseDatabase.getInstance().getReference("Room_Details")
                .child(myCurrentDateTime).setValue(roomData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Upload_Details.this, "Room Details Uploaded", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Upload_Details.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
