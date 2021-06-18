package com.example.landautomation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {
    RadioGroup r,ca,ga,lt;
    Button predict;
    Spinner loc;
    int divvalue;
    int regvalue;
    int cavalue;
    int gavalue;
    int ltvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        predict=(Button) findViewById(R.id.predictid);
        r=(RadioGroup)findViewById(R.id.regid);

        ca=(RadioGroup)findViewById(R.id.currentavailid);
        ga=(RadioGroup)findViewById(R.id.gasavailid);
        lt=(RadioGroup)findViewById(R.id.loadshedtid);
        loc=(Spinner)findViewById(R.id.locationid);
        if(!Python.isStarted())
            Python.start(new AndroidPlatform(this));
        Python py=Python.getInstance();
        final PyObject pyob=py.getModule("mylr");




        predict.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String    locc=loc.getSelectedItem().toString();
        int regcheck=r.getCheckedRadioButtonId();
        int curavailcheck=ca.getCheckedRadioButtonId();
        int gacheck=ga.getCheckedRadioButtonId();
        int ltcheck=lt.getCheckedRadioButtonId();
        if(regcheck==-1|| curavailcheck==-1||gacheck==-1||ltcheck==-1){
            Toast.makeText(MainActivity.this,"please select all info",Toast.LENGTH_LONG).show();
        }
        else {
            setloc(locc);
            setreg(regcheck);
            setcuravail(curavailcheck);
            setgasavail(gacheck);
            setlt(ltcheck);

            PyObject pob=pyob.callAttr("main",regvalue,divvalue,cavalue,gavalue,ltvalue);


           // predict.setText(pob.toString());
           // Toast.makeText(MainActivity.this ,pob.toString(),Toast.LENGTH_LONG).show();
            Intent intent =new Intent(MainActivity.this,graph_activity.class);
           startActivity(intent);

        }


    }


       });

    }

    private void setloc(String locc) {
        if (locc.equalsIgnoreCase("Dhaka")) {
            divvalue=3;

        }
        else {
            divvalue=1;
        }
    }

    private void setlt(int ltcheck) {
        switch (ltcheck){
            case R.id.tenid:
                ltvalue=4;
                break;

            case R.id.oneid:
                ltvalue=3;
                break;

            case R.id.twoid:
                ltvalue=2;
                break;
            case R.id.threeid:
                ltvalue=1;
                break;



        }
    }

    private void setgasavail(int gacheck) {
        switch (gacheck){
            case R.id.ygid:
                gavalue=1;
                break;
            case R.id.ngid:

                 gavalue=0;
                 break;

        }
    }

    private void setcuravail(int curavailcheck) {
        switch (curavailcheck){
            case R.id.ycid:
                cavalue=1;
                break;
            case R.id.ncid:
                cavalue=0;
                break;
        }
    }

    private void setreg(int regcheck) {
        switch (regcheck){
            case R.id.ctid:
                regvalue=10;
                break;
            case R.id.zid:
                regvalue=7;
                break;
            case R.id.pouid:
                regvalue=5;
                break;
            case R.id.rid:
                regvalue=1;
                break;

        }
    }
}
