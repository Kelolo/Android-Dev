package com.example.cz264.advancedcalc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;


public class CalcMainActivity extends Activity {
    boolean isOPeration = false;
    static Double res;
    double finalres;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear;
    ImageButton ibtnPlus, ibtnSubtract, ibtnMultiply, ibtnDivide, ibtnEqual;
    TextView resTv;
    Queue<Double> numq = new LinkedList<>();
    Queue<String> opq = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_main);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        btnClear = (Button) findViewById(R.id.btnClear);

        ibtnDivide = (ImageButton) findViewById(R.id.ibtnDivide);
        ibtnEqual = (ImageButton) findViewById(R.id.ibtnEqual);
        ibtnSubtract = (ImageButton) findViewById(R.id.ibtnSubtract);
        ibtnPlus = (ImageButton) findViewById(R.id.ibtnPlus);
        ibtnMultiply = (ImageButton) findViewById(R.id.ibtnMultiply);

        resTv = (TextView) findViewById(R.id.resTv);
        resTv.setText("0");


//        ibtnEqual listener
        ibtnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numq.offer(Double.parseDouble(resTv.getText().toString()));
                resTv.setText(String.valueOf(calc()));
            }
        });

//        other operation (ibtn) listener
        ibtnDivide.setOnClickListener(createIbtnListener("/"));
        ibtnMultiply.setOnClickListener(createIbtnListener("*"));
        ibtnPlus.setOnClickListener(createIbtnListener("+"));
        ibtnSubtract.setOnClickListener(createIbtnListener("-"));

//        btnClear listener
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                q = new LinkedList<Integer>();
                opq = new LinkedList<String>();
                numq = new LinkedList<Double>();
                resTv.setText("0");
            }
        });

//      number btn listener
        btn0.setOnClickListener(createListener(0));
        btn1.setOnClickListener(createListener(1));
        btn2.setOnClickListener(createListener(2));
        btn3.setOnClickListener(createListener(3));
        btn4.setOnClickListener(createListener(4));
        btn5.setOnClickListener(createListener(5));
        btn6.setOnClickListener(createListener(6));
        btn7.setOnClickListener(createListener(7));
        btn8.setOnClickListener(createListener(8));
        btn9.setOnClickListener(createListener(9));
    }


//  number button Listener creation method
    private View.OnClickListener createListener(int num) {
        final int temp = num;
        View.OnClickListener lis = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (resTv.getText().toString().equals("0") || isOPeration){
                    resTv.setText(String.valueOf(temp));
                    isOPeration = false;
                } else {
                    resTv.append(String.valueOf(temp));
                }
            }
        };

        return lis;
    }


//  operation button listener creation method
    private View.OnClickListener createIbtnListener(String operation) {
        final String temp = operation;
        final int tempres = 0;
        View.OnClickListener lis = new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if (isOPeration) {
                    resTv.setText("#Error: multi oper");
                } else {
                    isOPeration = true;
                    numq.offer(Double.parseDouble(resTv.getText().toString()));
                    opq.offer(temp);
                }
            }
        };

        return lis;
    }

    private double calc() {
//        res = 100.0;
        boolean isStart = true;

        while(!numq.isEmpty() || !opq.isEmpty()){
            if (isStart){

                isStart = false;
                res = numq.poll();

            } else {

                String op = opq.poll();
                double d = numq.poll();

                if (op.equals("+")){
                    res += d;
                } else if (op.equals("-")){
                    res -= d;
                } else if (op.equals("*")){
                    res *= d;
                } else {
                    res /= d;
                }
            }
        }
        opq = new LinkedList<>();
        numq = new LinkedList<>();
        return res;
    }
}
