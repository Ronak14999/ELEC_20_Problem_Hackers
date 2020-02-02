package com.example.testing_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<RoomData> myRoomList;

    private DatabaseReference databaseReference;
    private ValueEventListener valueEventListener;
    MyAdapter myAdapter;
   ProgressDialog progressDialog;
   EditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        mRecyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(MainActivity.this,1);
        mRecyclerView.setLayoutManager(gridLayoutManager);

        etSearch=(EditText)findViewById(R.id.etsearch) ;

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");

        myRoomList=new ArrayList<>();
         myAdapter=new MyAdapter(MainActivity.this,myRoomList);
        mRecyclerView.setAdapter(myAdapter);


        databaseReference= FirebaseDatabase.getInstance().getReference("Room_Details");
        progressDialog.show();
        valueEventListener=databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                myRoomList.clear();
                for(DataSnapshot itemSnapshot:dataSnapshot.getChildren()){
                    RoomData roomData=itemSnapshot.getValue(RoomData.class);
                    roomData.setKey(itemSnapshot.getKey());
                    myRoomList.add(roomData);

                }
                myAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
              progressDialog.dismiss();
            }
        });



        etSearch.addTextChangedListener((new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                filter(s.toString());

            }
        }));



    }


    private void filter(String text)
    {
        ArrayList<RoomData> filterList =new ArrayList<>();
        for(RoomData item:myRoomList){
            if(item.getArea().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }

        myAdapter.filteredList(filterList);
    }


    public void uploadbtn(View view) {

        startActivity(new Intent(this,Upload_Details.class));
    }

    public void advanceSearchbtn(View view) {

        startActivity(new Intent(this,SearchFilter.class));

    }
}
