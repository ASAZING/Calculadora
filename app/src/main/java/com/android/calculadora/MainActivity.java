package com.android.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
   private TextView _screen;
   private String display="";
   private String resultEnd="";
   private String cOperation="";
   private TextView _screenResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button m = (Button)findViewById(R.id.matrices);
        _screen = (TextView) findViewById(R.id.operation);
        _screen.setText(display);
        _screenResult = (TextView) findViewById(R.id.result);
        _screenResult.setText(display);
        MobileAds.initialize(this, "ca-app-pub-9150842075666198~9950308183");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


// TODO: Add adView to your view hierarchy


        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });
    }

    private  void updateScreen(){
        _screen.setText(display);
        _screenResult.setText(resultEnd);
    }
    protected void onClickNumber(View v){
        Button btn = (Button) v;
        if (display.length()<15){

            display += btn.getText();
            updateScreen();

        }

    }

    private boolean isOperation(char ope){

        switch (ope) {
            case '%':
            case '+':
            case '-':
            case 'x':
            case '÷':
                return true;
            default:
                return false;

        }


    }
    protected void onClickOperation(View v){

            if (display == "") return;

            Button b = (Button) v;

            if (resultEnd != "") {
                String _display = resultEnd;
                clear();
                display = _display;

            }

            if (cOperation != "") {
                Log.d("CalcX", "" + display.charAt(display.length() - 1));
                if (isOperation(display.charAt(display.length() - 1))) {
                    display = display.replace(display.charAt(display.length() - 1), b.getText().charAt(0));
                    updateScreen();
                    return;
                } else {
                    getResult();
                    display = resultEnd;


                }
                cOperation = b.getText().toString();
            }
            display += b.getText();
            cOperation = b.getText().toString();
            updateScreen();
    }

    private boolean getResult(){
        if(cOperation == "") return false;
        String[] operation = display.split(Pattern.quote(cOperation));
        if(operation.length < 2) return false;
        resultEnd = String.valueOf(operate(operation[0], operation[1], cOperation));
        return true;


    }

    protected void onClickEq(View v) {
        if(display == "") return;
        if(!getResult()) return;
        _screenResult.setText(resultEnd);
    }
    protected void onClickClear(View v) {
        clear();
        updateScreen();
    }

    protected void clear() {
        display ="";
        cOperation ="";
        resultEnd="";
        _screenResult.setText("0");
    }

    protected void deleteChar(){
        display = (display.substring(0,display.length()-1));
    }

    protected void onClickClearChar(View v) {
        try {
            if (display.length() < 1) {
                Toast toast1 =
                        Toast.makeText(getApplicationContext(),
                                "Clean", Toast.LENGTH_SHORT);
                toast1.show();

            } else {
                deleteChar();
                updateScreen();
            }
        }catch (Exception e){

            Toast toast =
                    Toast.makeText(getApplicationContext(),
                            "Error :"+ e, Toast.LENGTH_SHORT);

            toast.show();
        }
    }

    private float operate(String a, String b, String op) {

            switch (op) {
                case "+":
                    return (Float.valueOf(a)) + (Float.valueOf(b));
                case "-":
                    return (Float.valueOf(a)) - (Float.valueOf(b));
                case "x":
                    return (Float.valueOf(a)) * (Float.valueOf(b));
                case "÷":
                    try {
                    return  (Float.valueOf(a)) / (Float.valueOf(b));
                    } catch (Exception e) {
                        Log.d("Calc", e.getMessage());

                    }
                case "%":
                    float p = Float.parseFloat(b);
                    return (Float.valueOf(a)) * (Float.valueOf(p/100));


                default:
                    return -1;


            }
    }
}
