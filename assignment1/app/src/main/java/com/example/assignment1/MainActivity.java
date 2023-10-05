package com.example.assignment1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    static DecimalFormat rounder = new DecimalFormat("#." + new String(new char[2]).replace('\0', '0'));

    Button calculate;
    EditText editText_amount, editText_rate, editText_period;
    TextView answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Create content view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup view object refs
        calculate = findViewById(R.id.calculateButton);
        editText_amount = findViewById(R.id.principle);
        editText_rate = findViewById(R.id.rate);
        editText_period = findViewById(R.id.amor);
        answer = findViewById(R.id.answer);

        // Setup calculate button listener
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the input text as numbers
                long amount = editText_amount.getText().toString().isEmpty() ? 0 : Long.parseLong(editText_amount.getText().toString());
                float rate = editText_amount.getText().toString().isEmpty() ? 0 : Float.parseFloat(editText_rate.getText().toString());
                int period = editText_amount.getText().toString().isEmpty() ? 0 : Integer.parseInt(editText_period.getText().toString());

                // Convert interest rate to monthly
                float monthlyRate = (rate / 12) / 100;

                // Calculate equated monthly installment
                double emi = (amount * monthlyRate * Math.pow(1 + monthlyRate, period)) / (Math.pow(1 + monthlyRate, period) - 1);

                // Display answer
                answer.setText("$" + rounder.format(emi));
            }

        });
    }

}