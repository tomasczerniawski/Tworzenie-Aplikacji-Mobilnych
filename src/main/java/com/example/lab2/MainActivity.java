package com.example.lab2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final Button[][] buttons = new Button[3][3];
    private boolean player1Turn = true;
    private int roundCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
        Button button = (Button) gridLayout.getChildAt(i);
        button.setOnClickListener(this);
        buttons[i/3][i%3] = button;
    }
    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) {
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {
            ((Button) v).setText("O");
        }

        roundCount++;

        if (checkForWin()) {
            if (player1Turn) {
                Toast.makeText(this, "Player 1 wins!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Player 2 wins!", Toast.LENGTH_SHORT).show();
            }
            resetBoard();
        } else if (roundCount == 9) {
            Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
            resetBoard();
        } else {
            player1Turn = !player1Turn;
        }
    }

    private boolean checkForWin() {
        String[][] board = new String[3][3];
        for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
        board[i][j] = buttons[i][j].getText().toString();
    }
    }

        // Check rows
        for (int i = 0; i < 3; i++) {
        if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals("")) {
            return true;
        }
    }

        // Check columns
        for (int i = 0; i < 3; i++) {
        if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals("")) {
            return true;
        }
    }

        // Check diagonals
        if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals("")) {
            return true;
        }

        return board[0][2].equals(board[1][1]) && board[0][2].equals(board[2][0]) && !board[0][2].equals("");
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
        buttons[i][j].setText("");
    }
    }
        roundCount = 0;
        player1Turn = true;
    }
}
