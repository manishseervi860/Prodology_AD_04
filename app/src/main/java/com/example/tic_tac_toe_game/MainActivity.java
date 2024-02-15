package com.example.tic_tac_toe_game;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final int GRID_SIZE = 3;
    private Button[][] buttons;
    private boolean playerXTurn = true;
    private boolean gameOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Initialize buttons grid
        buttons = new Button[GRID_SIZE][GRID_SIZE];
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j] = (Button) gridLayout.getChildAt(i * GRID_SIZE + j);
            }
        }
    }

    // Called when a cell is clicked
    public void onCellClicked(View view) {
        if (gameOver) return;

        Button button = (Button) view;
        if (button.getText().toString().isEmpty()) {
            if (playerXTurn)
                button.setText("X");
            else
                button.setText("O");

            playerXTurn = !playerXTurn;
            checkForWin();
        }
    }

    // Check if someone has won the game
    private void checkForWin() {
        String[][] field = new String[GRID_SIZE][GRID_SIZE];
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }

        // Check rows, columns, and diagonals
        for (int i = 0; i < GRID_SIZE; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2]) && !field[i][0].isEmpty()) {
                gameOver = true;
                announceWinner(field[i][0]);
                return;
            }

            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i]) && !field[0][i].isEmpty()) {
                gameOver = true;
                announceWinner(field[0][i]);
                return;
            }
        }

        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2]) && !field[0][0].isEmpty()) {
            gameOver = true;
            announceWinner(field[0][0]);
            return;
        }

        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0]) && !field[0][2].isEmpty()) {
            gameOver = true;
            announceWinner(field[0][2]);
        }
    }

    // Announce the winner
    private void announceWinner(String winner) {
      //  Toast.makeText(this, "Player " + winner + " wins!", Toast.LENGTH_SHORT).show();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setTitle("Winner");
        builder.setMessage("Player " + winner + " wins!");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reset();
            }
        });
        builder.show();


    }

    // Reset the game
    public void onResetClicked(View view) {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        playerXTurn = true;
        gameOver = false;
    }

    public void reset() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                buttons[i][j].setText("");
            }
        }
        playerXTurn = true;
        gameOver = false;
    }
}