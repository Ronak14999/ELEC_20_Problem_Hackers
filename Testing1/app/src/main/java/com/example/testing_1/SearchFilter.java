package com.example.testing_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SearchFilter extends AppCompatActivity {

    Spinner sstate, scity, scoaching;
    String State,City,Coaching;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);

        sstate = (Spinner) findViewById(R.id.selectState);
        scity = (Spinner) findViewById(R.id.selectCity);
        scoaching = (Spinner) findViewById(R.id.selectCoachingInstitute);


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


        List<String> coaching=new ArrayList<String>();
        coaching.add("Select Coaching Institute");
        coaching.add("Apollo Classes");
        coaching.add("Aryabhatta Sansthan");
        coaching.add("Akash Institute ");
        coaching.add("Allen Sankalp ");
        coaching.add("Allen Samanvaya ");
        coaching.add("Allen Sangyan ");
        coaching.add("Allen Sangyan-2");
        coaching.add("Allen Satyarth");
        coaching.add("Allen Satyarth-2");
        coaching.add("Allen Supath");
        coaching.add("Allen Samarth ");
        coaching.add("Allen Sabal ");
        coaching.add("Allen Safalya ");
        coaching.add("Allen Saakar ");
        coaching.add("Allen Saakar-2 ");
        coaching.add("Allen Sarokar");
        coaching.add("Allen Sarokar-2 ");
        coaching.add("Allen Savinay ");
        coaching.add("Allen Sukalp ");
        coaching.add("Allen Samayik ");
        coaching.add("Allen Samyak");
        coaching.add("Bansal Classes");
        coaching.add("Career Point");
        coaching.add("Fiitjee Center");
        coaching.add("Nucleus Academy");
        coaching.add("Real Coaching Classes");
        coaching.add("Resonance CG Tower");
        coaching.add("Resonance Medical Division");
        coaching.add("Resonance CLPD Division");


        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, coaching);
        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        scoaching.setAdapter(arrayAdapter2);
        scoaching.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                scoaching.setSelection(position);
                Coaching = scoaching.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void searchbtn(View view) {

        startActivity(new Intent(this,MainActivity.class));

    }
}
