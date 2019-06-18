package com.abdulrohman.calculate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText result;
    private EditText newNumber;
    private TextView opration;
    // define varible
    private Double opereand1 = null;
    private Double opreand2 = null;
    private String opreanEqual = "=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        opration = findViewById(R.id.txtOpration);
        newNumber = findViewById(R.id.edtTxtNewNumber);
        result = findViewById(R.id.edtTxtResult);
        Button btn0 = findViewById(R.id.btn0);
        Button btn1 = findViewById(R.id.btn1);
        Button btn2 = findViewById(R.id.btn2);
        Button btn3 = findViewById(R.id.btn3);
        Button btn4 = findViewById(R.id.btn4);
        Button btn5 = findViewById(R.id.btn5);
        Button btn6 = findViewById(R.id.btn6);
        Button btn7 = findViewById(R.id.btn7);
        Button btn8 = findViewById(R.id.btn8);
        Button btn9 = findViewById(R.id.btn9);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnMul = findViewById(R.id.btnMul);
        Button btndevid = findViewById(R.id.btndevid);
        Button btnComma = findViewById(R.id.btnComma);
        Button btnequal = findViewById(R.id.btnequal);
        Button buttonadd = findViewById(R.id.buttonadd);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;

                newNumber.append(button.getText().toString());
            }
        };

        btn0.setOnClickListener(listener);
        btn1.setOnClickListener(listener);
        btn2.setOnClickListener(listener);
        btn3.setOnClickListener(listener);
        btn4.setOnClickListener(listener);
        btn5.setOnClickListener(listener);
        btn6.setOnClickListener(listener);
        btn7.setOnClickListener(listener);
        btn8.setOnClickListener(listener);
        btn9.setOnClickListener(listener);

        View.OnClickListener listenerOp = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                String op = button.getText().toString();
                String strCheckInput = newNumber.getText().toString();
                Double newDouble;
                if (strCheckInput.isEmpty()){
                    newDouble=0.0;
                }else {
                    newDouble = Double.valueOf(strCheckInput);
                }
                try {
                    calcul(newDouble, op);
                } catch (NumberFormatException e) {
                    newNumber.setText("");
                }
                opreanEqual = op;
                opration.setText(opreanEqual);
            }
        };
        btnequal.setOnClickListener(listenerOp);
        btndevid.setOnClickListener(listenerOp);
        btnMinus.setOnClickListener(listenerOp);
        btnMul.setOnClickListener(listenerOp);
        buttonadd.setOnClickListener(listenerOp);

        View.OnClickListener listenerClear = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opereand1 = null;
                opreand2 = null;
                result.setText("");
                newNumber.setText("");
                opration.setText("");
                opreanEqual = "=";

            }
        };
        btnComma.setOnClickListener(listenerClear);
    }

    public void calcul(Double val, String op) {
        try {
            if (opereand1 == null) {
                opereand1 = val;
            } else {
                opreand2 = val;
                if (opreanEqual.equals("=")) {
                    opreanEqual = op;
                }
                switch (opreanEqual) {
                    case "=":
                        opereand1 = opreand2;
                        break;
                    case "*":
                        opereand1 *= opreand2;
                        break;
                    case "+":
                        opereand1 += opreand2;
                        break;
                    case "-":
                        opereand1 -= opreand2;
                        break;
                    case "/":
                        if (opreand2 == 0) {
                            opereand1 = 0.0;
                        } else {
                            opereand1 /= opreand2;
                        }
                        break;
                }


            }
        }catch (Exception e){

        }
        result.setText(opereand1.toString());
        newNumber.setText("");

    }

    private static final String opreand1 = "opreand1";
    private static final String opreation = "opreation";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if(opereand1 != null) {
            outState.putDouble(opreand1, opereand1);
        }
        outState.putString(opreation, opreanEqual);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        opereand1 = savedInstanceState.getDouble(opreand1);
        opration.setText(savedInstanceState.getString(opreation));
    }



}
