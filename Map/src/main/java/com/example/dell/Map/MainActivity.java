package com.example.dell.projectx;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dell.projectx.model.Memory;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Memory memory;
    private EditText desc;
    private  EditText date;
    double   lat;
    double lng;


    private static final String TAG = "MainActivity";
    private EditText mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;


// **************** start DatePickerDialog ***********************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayDate =  findViewById(R.id.date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        // **************** end DatePickerDialog ***********************

        desc = findViewById(R.id.desc);
        date = findViewById(R.id.date);

        Intent intent = getIntent();
        lat = intent.getDoubleExtra("lat",0);

         lng = intent.getDoubleExtra("long",0);
        memory = new Memory();
        memory.setLat(lat);
        memory.setLang(lng);
        memory.setDescription(desc.getText().toString());
        memory.setData(desc.getText().toString());
    }
    public void saveMemory(View view){
        Memory memory1 = new Memory("desc" , lat , lng , "ahhaha");
        MemoryDataBase.getInstance(this).mimerosDaos().InsertMemory(memory1);
    }
}
