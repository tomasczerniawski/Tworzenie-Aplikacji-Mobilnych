package com.example.kontrolna;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText inputEditText;
    private Button countButton;
    private TextView resultTextView;
    private List<String> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputEditText = findViewById(R.id.inputEditText);
        countButton = findViewById(R.id.countButton);
        resultTextView = findViewById(R.id.resultTextView);
        resultList = new ArrayList<>();

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputEditText.getText().toString().trim();

                if (!input.isEmpty() && input.matches("[0-9]+")) {
                    double number = Double.parseDouble(input);
                    double squareRoot = Math.sqrt(number);
                    String result = String.format("%.2f", squareRoot);

                    resultList.add(result);
                    if (resultList.size() > 5) {
                        resultList.remove(0);
                    }

                    displayResults();
                } else {
                    resultTextView.setText("Invalid input. Please enter a valid number.");
                }
            }
        });
    }

    private void displayResults() {
        StringBuilder builder = new StringBuilder();

        for (String result : resultList) {
            builder.append(result).append("\n");
        }

        resultTextView.setText(builder.toString());
    }
}
