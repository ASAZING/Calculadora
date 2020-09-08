package com.android.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MatricesActivity extends AppCompatActivity{


    
    private int dmx=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrices);

        final CheckBox dxd = (CheckBox) findViewById(R.id.twoxtwo);
        final CheckBox txt = (CheckBox) findViewById(R.id.threexthree);
        final CheckBox fxf = (CheckBox) findViewById(R.id.fourxfour);
        final EditText a11 = (EditText) findViewById(R.id.a11);
        final EditText a12 = (EditText) findViewById(R.id.a12);
        final EditText a13 = (EditText) findViewById(R.id.a13);
        final EditText a14 = (EditText) findViewById(R.id.a14);
        final EditText a21 = (EditText) findViewById(R.id.a21);

        final EditText a24 = (EditText) findViewById(R.id.a24);
        final EditText a31 = (EditText) findViewById(R.id.a31);

        final EditText a34 = (EditText) findViewById(R.id.a34);
        final EditText a41 = (EditText) findViewById(R.id.a41);
        final EditText a42 = (EditText) findViewById(R.id.a42);
        final EditText a43 = (EditText) findViewById(R.id.a43);
        final EditText a44 = (EditText) findViewById(R.id.a44);


        dxd.setChecked(true);

        dxd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                   dmx =1;
                   txt.setChecked(false);
                   fxf.setChecked(false);

                    a11.setVisibility(View.INVISIBLE);
                    a12.setVisibility(View.INVISIBLE);
                    a13.setVisibility(View.INVISIBLE);
                    a14.setVisibility(View.INVISIBLE);
                    a21.setVisibility(View.INVISIBLE);
                    a24.setVisibility(View.INVISIBLE);
                    a31.setVisibility(View.INVISIBLE);
                    a34.setVisibility(View.INVISIBLE);
                    a41.setVisibility(View.INVISIBLE);
                    a42.setVisibility(View.INVISIBLE);
                    a43.setVisibility(View.INVISIBLE);
                    a44.setVisibility(View.INVISIBLE);
                }
            }
        });
        txt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dmx =2;
                    a11.setVisibility(View.VISIBLE);
                    a12.setVisibility(View.VISIBLE);
                    a13.setVisibility(View.VISIBLE);
                    a21.setVisibility(View.VISIBLE);
                    a31.setVisibility(View.VISIBLE);
                    a14.setVisibility(View.INVISIBLE);
                    a24.setVisibility(View.INVISIBLE);
                    a34.setVisibility(View.INVISIBLE);
                    a41.setVisibility(View.INVISIBLE);
                    a42.setVisibility(View.INVISIBLE);
                    a43.setVisibility(View.INVISIBLE);
                    a44.setVisibility(View.INVISIBLE);
                    dxd.setChecked(false);
                    fxf.setChecked(false);
                }
            }
        });
        fxf.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    dmx =3;
                    a11.setVisibility(View.VISIBLE);
                    a12.setVisibility(View.VISIBLE);
                    a13.setVisibility(View.VISIBLE);
                    a14.setVisibility(View.VISIBLE);
                    a21.setVisibility(View.VISIBLE);
                    a24.setVisibility(View.VISIBLE);
                    a31.setVisibility(View.VISIBLE);
                    a34.setVisibility(View.VISIBLE);
                    a41.setVisibility(View.VISIBLE);
                    a42.setVisibility(View.VISIBLE);
                    a43.setVisibility(View.VISIBLE);
                    a44.setVisibility(View.VISIBLE);
                    dxd.setChecked(false);
                    txt.setChecked(false);


                }
            }
        });

    }



    protected void setDmx(){
        final EditText a32 = (EditText) findViewById(R.id.a32);
        final EditText a33 = (EditText) findViewById(R.id.a33);
        final EditText a22 = (EditText) findViewById(R.id.a22);
        final EditText a23 = (EditText) findViewById(R.id.a23);
        float determ;
        float a,b,c,d;

        switch (dmx){
            case 1:
                a = Float.parseFloat(String.valueOf(a22.getText()));
                b = Float.parseFloat(String.valueOf(a23.getText()));
                c = Float.parseFloat(String.valueOf(a32.getText()));
                d = Float.parseFloat(String.valueOf(a33.getText()));
                /** Operacion Determinate **/
                determ = (a*d)-((b*c));
                //ai=1/determ;
                if (determ==0) {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Esta matriz no tiene Inversa" +
                                            "\n"+a + "   " +b+
                                            "\n" +c + "   " +d, Toast.LENGTH_SHORT);
                    toast1.show();

                    break;
                } else {
                    //String det = String.valueOf(determ);
                   // val_det.setText(det);
                    /** Operacion Matriz Transpuesta **/
                    Intent intent = new Intent(MatricesActivity.this, FragmentActivity.class);
                    startActivity(intent);

                    Bundle bundle1 = new Bundle();
                    Bundle bundle2 = new Bundle();
                    Bundle bundle3 = new Bundle();
                    Bundle bundle4 = new Bundle();

                    String uno = String.valueOf(a);
                    bundle1.putString("a21", uno);
                    intent.putExtras(bundle1);
                    String dos = String.valueOf(c);
                    bundle2.putString("a22", dos);
                    intent.putExtras(bundle2);
                    String tres = String.valueOf(b);
                    bundle3.putString("a31", tres);
                    intent.putExtras(bundle3);
                    String cuatr = String.valueOf(d);
                    bundle4.putString("a32", cuatr);
                    intent.putExtras(bundle4);

                    /** Operacion Matriz adjunta

                    String ad11 = String.valueOf(d);
                    a11a.setText(ad11);
                    String ad12 = String.valueOf(-(b));
                    a12a.setText(ad12);
                    String ad21 = String.valueOf(-(c));
                    a21a.setText(ad21);
                    String ad22 = String.valueOf(a);
                    a22a.setText(ad22);

                     Operacion Matriz Inverza

                    String i11 = String.valueOf(d * ai);
                    a11i.setText(i11);
                    String i12 = String.valueOf(-(b) * ai);
                    a12i.setText(i12);
                    String i21 = String.valueOf(-(c) * ai);
                    a21i.setText(i21);
                    String i22 = String.valueOf(a * ai);
                    a22i.setText(i22);**/


                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "2x2", Toast.LENGTH_SHORT);
                    toast1.show();
                }

                    break;

            case 2:
                Toast toast2 =
                        Toast.makeText(getApplicationContext(),
                                "3x3", Toast.LENGTH_SHORT);
                toast2.show();
                break;
            case 3:
                Toast toast3 =
                        Toast.makeText(getApplicationContext(),
                                "4x4", Toast.LENGTH_SHORT);
                toast3.show();
                break;

        }

    }

    protected void OnclickSetButt(View v){
        setDmx();

    }
}
