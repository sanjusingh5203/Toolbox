package com.example.sanjuparihar.toolbox;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Calculator extends AppCompatActivity {

    Button button0 , button1 , button2 , button3 , button4 , button5 , button6 ,
            button7 , button8 , button9 , buttonAdd , buttonSub , buttonDivision ,
            buttonMul , buttondot , buttonC , buttonEqual ;
    TextView edt1,edt2;
    float var1 , var2 ;

    boolean mAddition , mSubtract ,mMultiplication ,mDivision ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        button0 = (Button) findViewById(R.id.btn0);
        button1 = (Button) findViewById(R.id.btn1);
        button2 = (Button) findViewById(R.id.btn2);
        button3 = (Button) findViewById(R.id.btn3);
        button4 = (Button) findViewById(R.id.btn4);
        button5 = (Button) findViewById(R.id.btn5);
        button6 = (Button) findViewById(R.id.btn6);
        button7 = (Button) findViewById(R.id.btn7);
        button8 = (Button) findViewById(R.id.btn8);
        button9 = (Button) findViewById(R.id.btn9);
        buttondot = (Button) findViewById(R.id.btndot);
        buttonAdd = (Button) findViewById(R.id.btnadd);
        buttonSub = (Button) findViewById(R.id.btnsub);
        buttonMul = (Button) findViewById(R.id.btnmul);
        buttonDivision = (Button) findViewById(R.id.btndiv);
        buttonC = (Button) findViewById(R.id.btnC);
        buttonEqual = (Button) findViewById(R.id.btneql);
        edt1=(TextView)findViewById(R.id.resultArea);
        edt2=(TextView)findViewById(R.id.resultArea2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"1");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"2");
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"3");
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"4");
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"5");
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"6");
            }
        });

        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"7");
            }
        });

        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"8");
            }
        });

        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"9");
            }
        });

        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText(edt1.getText()+"0");
            }
        });



        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                if (edt1 == null){
                    edt1.setText("");

                }else {
                    var1 = Float.parseFloat(edt1.getText() + "");
                    mAddition = true;
                    edt1.setText(null);
                }
            }catch (RuntimeException e){
                    Log.e(getString(R.string.app_name), "Error");

                    e.printStackTrace();
                }
            }
        });

        buttonSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                        var1 = Float.parseFloat(edt1.getText() + "");
                        mSubtract = true;
                        edt1.setText(null);
                } catch (RuntimeException e) {
                    Log.e(getString(R.string.app_name), "Error");

                    e.printStackTrace();
                }
            }
        });

        buttonMul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                    try {
                        var1 = Float.parseFloat(edt1.getText() + "");
                        mMultiplication = true;
                        edt1.setText(null);
                    } catch (RuntimeException e) {
                        Log.e(getString(R.string.app_name), "Error");

                        e.printStackTrace();
                    }
                }
        });

        buttonDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    var1 = Float.parseFloat(edt1.getText() + "");
                    mDivision = true;
                    edt1.setText(null);
                } catch (RuntimeException e) {
                    Log.e(getString(R.string.app_name), "Error");

                    e.printStackTrace();
                }
            }
        });

        buttonEqual.setOnClickListener(new View.OnClickListener()  {

                @Override

                public void onClick (View v){
                    try {
                        var2 = Float.parseFloat(edt1.getText() + "");

                        if (mAddition == true) {

                            edt2.setText(var1 + var2 + "");
                            mAddition = false;
                            edt1.setText(null);
                            edt1.setText(edt2.getText());
                        }


                        if (mSubtract == true) {


                                edt2.setText(var1 - var2 + "");
                                mSubtract = false;
                                edt1.setText(null);
                                edt1.setText(edt2.getText());
                        }

                        if (mMultiplication == true) {
                            edt2.setText(var1 * var2 + "");
                            mMultiplication = false;
                            edt1.setText(null);
                            edt1.setText(edt2.getText());
                        }

                        if (mDivision == true) {
                            edt1.setText(var1 / var2 + "");

                            mDivision = false;
                            edt1.setText(null);
                            edt1.setText(edt2.getText());
                        }
                    } catch (RuntimeException e)

                    {
                        e.printStackTrace();
                    }
                }

        });


        buttondot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(var1==0)
                edt1.setText(0+".");
                else
                 edt1.setText(".");
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt1.setText("");
                edt2.setText("");
            }
        });
    }



}